package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.po.User;
import com.lacey.authority.entity.po.UserRole;
import com.lacey.authority.entity.to.UserSaveTo;
import com.lacey.authority.entity.vo.*;
import com.lacey.authority.mapper.RoleMapper;
import com.lacey.authority.mapper.UserMapper;
import com.lacey.authority.mapper.UserRoleMapper;
import com.lacey.authority.service.UserService;
import com.mysql.cj.xdevapi.UpdateParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public CustomPage<UserListVO> getUserTable(int pageNum, int pageSize, String name) {
        //使用mybatis plus的page类封装分页参数----->当前页码和页码容量参数
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        //然后使用mybatis plus的分页查询方法查询数据
        IPage<User> pageDate;
        if (StringUtils.isBlank(name)) {
            pageDate = userMapper.selectPage(pageParam, new QueryWrapper<User>());
        } else {
            pageDate = userMapper.selectPage(pageParam, new QueryWrapper<User>().like("name", name));
        }
        //然后将分页数据拿出来
        List<User> users = pageDate.getRecords();

        // 到这里后，分页的用户数据已经出来了，只剩下角色的，我们可以简化下面的sql语句，简化到只存在两条sql语句，
        // 这里可以先循环下用户表，把用户名单拿出来形成一个String集合，就是用户名的集合，
        // 然后将这些用户名的集合作为where 里in的条件，一次性把用户名和角色id的关联数据全查出来形成UserRole的对象集合
        // 再然后就是直接for循环从集合中筛选出想要的数据就行了。
        // 这样循环里连那个根据用户名查询角色id的SQL都不用写了
        List<String> userNames = new ArrayList<>();
        for (User user : users) {
            userNames.add(user.getName());
        }
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().in("userName", userNames));

        //这里是根据id查询角色详情数据的，如果写在循环里的话，在用户数量很多的情况下，比较浪费性能
        // 一般来说，用户是比较多的，但是角色一般比较少，因为一个公司一般也就那么几个职位和角色，
        // 所以呢，这里的逻辑可以拿出来，拿到用户的循环外面，直接全表查询角色数据形成角色的对象集合，
        // 然后这里循环的时候就不用再去一遍遍的查询角色信息了，直接for循环下刚才拿到的全表的角色集合，
        // 这样的话，角色表的查询语句就简化到了一条SQL语句，而不是用户循环里，每循环一个用户，就查一次角色表
        List<Role> roleList = roleMapper.selectList(new QueryWrapper<>());

        // 这里我们已经拿到了【用户名和角色id】的绑定数据，还有【角色表】数据
        // 现在我们先把这两种数据整合，整合成【用户名和角色名】的绑定数据，即把角色id替换为角色名
        List<UserRoleListVO> userRoleListVOS = new ArrayList<>();
        for (Role role : roleList) {
            for (UserRole userRole : userRoles) {
                if (userRole.getRoleId().equals(role.getId())) {
                    UserRoleListVO userRoleListVO = new UserRoleListVO();
                    userRoleListVO.setUserName(userRole.getUserName());
                    userRoleListVO.setRoleName(role.getName());
                    userRoleListVOS.add(userRoleListVO);
                }
            }
        }

        //到这里，【用户表】数据、【用户名和角色名】绑定数据都已经拿到，现在只需要for循环遍历去获取出来
        List<UserListVO> userListVOList = new ArrayList<>();
        for (User user : users) {
            UserListVO userListVO = new UserListVO();
            //先将user表基础数据赋给用户列表的封装类UserListVO
            userListVO.setId(user.getId());
            userListVO.setName(user.getName());

            //然后再去循环【用户名和角色名】获取角色名称
            List<String> roles = new ArrayList<>();
            for (UserRoleListVO userRoleListVO : userRoleListVOS) {
                if (user.getName().equals(userRoleListVO.getUserName())) {
                    roles.add(userRoleListVO.getRoleName());
                }
            }
            userListVO.setRoles(roles);
            userListVOList.add(userListVO);
        }
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setTotal((long) userListVOList.size());
        CustomPage<UserListVO> customPage = new CustomPage<>();
        customPage.setPage(pagination);
        customPage.setData(userListVOList);
        return customPage;
    }

    @Override
    public boolean insertUser(UserSaveTo userSaveTo) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userSaveTo.getName());
        int result = userMapper.insert(user);
        if (result < 0) {
            return false;
        }

//        if (CollectionUtils.isEmpty(userSaveTo.getAddRoles())){
//            return new ArrayList<>();
//        }
        List<UserRole> userRoles = new ArrayList<>();
        for (String addRoles : userSaveTo.getAddRoles()) {
            UserRole userRole = new UserRole();
            userRole.setUserName(userSaveTo.getName());
            userRole.setRoleId(addRoles);
            userRoles.add(userRole);
        }
        result = userRoleMapper.insertBatch(userRoles);
        if (result < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(UserRoleUpVO userRoleUpVO) {
        // 先根据id查出旧数据
        User oldUser = userMapper.selectById(userRoleUpVO.getId());

        // 开始进行update逻辑
        User user = new User();
        user.setId(userRoleUpVO.getId());
        user.setName(userRoleUpVO.getName());
        boolean result = updateById(user);//修改用户表

        // 然后判断下用户名是否做过修改，如果做过修改，那么在修改用户角色关联表的绑定数据前，
        // 先修改下用户名，避免两表用户名不一致
        if (!oldUser.getName().equals(userRoleUpVO.getName())) {
            int count = userRoleMapper.updateUserName(oldUser.getName(), userRoleUpVO.getName());
            if (count < 0) {
                return false;
            }
        }
        int count;
        if (!CollectionUtils.isEmpty(userRoleUpVO.getDeleteRoles())){
            count = userRoleMapper.delete(new QueryWrapper<UserRole>().in("roleId", userRoleUpVO.getDeleteRoles())
                    .eq("userName", userRoleUpVO.getName()));//要删除绑定的角色，不止要匹配角色id，还要匹配用户名，万一也有别的用户绑定了这个角色，岂不是也被删了
            if (count < 0) {
                return false;
            }
        }
        UserRole userRole = new UserRole();
        if (!CollectionUtils.isEmpty(userRoleUpVO.getAddRoles())){
            List<UserRole> userRoles = new ArrayList<>();
            for (String addRoles : userRoleUpVO.getAddRoles()) {
                userRole.setUserName(user.getName());
                userRole.setRoleId(addRoles);
                userRoles.add(userRole);
            }
            count = userRoleMapper.insertBatch(userRoles);//要新增绑定的角色
            if (count < 0) {
                return false;
            }
        }

        return result;
    }

    @Override
    public boolean deleteUser(String id) {
        User user = userMapper.selectById(id);
        int count = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("userName",user.getName()));
        if (count<0){
            return false;
        }
        count = userMapper.deleteById(id);
        if (count<0){
            return false;
        }
        return true;
    }
}

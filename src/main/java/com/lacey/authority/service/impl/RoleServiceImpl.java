package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.po.UserRole;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.Pagination;
import com.lacey.authority.entity.vo.RoleListVO;
import com.lacey.authority.mapper.FunctionRoleMapper;
import com.lacey.authority.mapper.RoleMapper;
import com.lacey.authority.mapper.UserRoleMapper;
import com.lacey.authority.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FunctionRoleMapper functionRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public CustomPage<RoleListVO> getRoleTable(int pageNum, int pageSize, String name) {
        //使用mybatis plus的page类封装分页参数--->当前页码和页码容量参数
        Page<Role> pageParam = new Page<>(pageNum,pageSize);
        //然后使用mybatis plus的分页查询方法查询数据
        IPage<Role> pageDate;
        if (StringUtils.isBlank(name)){
            pageDate = roleMapper.selectPage(pageParam,new QueryWrapper<Role>());
        }else {
            pageDate = roleMapper.selectPage(pageParam,new QueryWrapper<Role>().like("name",name));
        }
        //然后将分页数据拿出来
        List<Role> roles = pageDate.getRecords();
        List<RoleListVO> roleListVOS = new ArrayList<>();
        for (Role role:roles){
            RoleListVO roleListVO = new RoleListVO();
            roleListVO.setId(role.getId());
            roleListVO.setName(role.getName());
            roleListVO.setCode(role.getCode());
            roleListVOS.add(roleListVO);
        }
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setTotal((long) roleListVOS.size());
        CustomPage<RoleListVO> customPage = new CustomPage<>();
        customPage.setPage(pagination);
        customPage.setData(roleListVOS);
        return customPage;
    }

    @Override
    public boolean insertRole(RoleSaveTO roleSaveTO) {
        //向角色表插入数据
        Role role = buildRole(roleSaveTO);
        int result = roleMapper.insert(role);
        if (result<0){
            return false;
        }
        //向功能表的绑定数据插入数据
        roleSaveTO.setId(role.getId());//因为新增的时候roleSaveTo对象里没有roleId的属性值
        List<FunctionRole> functionRole = buildFunctionRole(roleSaveTO);
        result = functionRoleMapper.insertBatch(functionRole);
        if (result<0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRole(RoleSaveTO roleSaveTO) {
        Role role = buildRole(roleSaveTO);
        boolean result= updateById(role);
        List<FunctionRole> functionRole = buildFunctionRole(roleSaveTO);
        //先删除原有关联数据，根据角色Id删除
        // 因为这个FunctionRoleMapper进行操作的时候，是在RoleService这个类下面的，这个类定义的实体类是Role，
        // 所以如果要在这个类里操作别的表的实体类，就要在<>里标明类，因为你是在人家的地盘下干活的，要写上你属于哪里，家住何方等等
        int count = functionRoleMapper.delete(new QueryWrapper<FunctionRole>().eq("roleId", role.getId()));
        if (count<0){
            return false;
        }
        count = functionRoleMapper.insertBatch(functionRole);
        if (count<0){
            return false;
        }
        return result;
    }

    @Override
    public boolean deleteRole(String id) {
        Role role = roleMapper.selectById(id);
        int count = functionRoleMapper.delete(new QueryWrapper<FunctionRole>().eq("roleId",role.getId()));
        if (count<0){
            return false;
        }
        count = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("roleId",role.getId()));
        if (count<0){
            return false;
        }
        count = roleMapper.deleteById(role.getId());
        if (count<0){
            return false;
        }
        return true;
    }

    @Override
    public RoleSaveTO getRoleDetailListById(String id) {
        Role role = roleMapper.selectById(id);
        //查询出角色和function的关联数据，并生成对应的functionId
        List<FunctionRole> functionRoles = functionRoleMapper.selectList(new QueryWrapper<FunctionRole>().eq("roleId",role.getId()));
        List<String> functionId = new ArrayList<>();
        if (!CollectionUtils.isEmpty(functionRoles)){
            for (FunctionRole functionRole : functionRoles){
                functionId.add(functionRole.getFunctionId());
            }
        }
        //封装要返回的数据
        RoleSaveTO roleSaveTO = new RoleSaveTO();
        roleSaveTO.setId(id);
        roleSaveTO.setCode(role.getCode());
        roleSaveTO.setName(role.getName());
        roleSaveTO.setDescription(role.getDescription());
        roleSaveTO.setFunctionIds(functionId);
        return roleSaveTO;
    }


    /**
     * 封装保存角色表实体类
     * @param roleSaveTO
     * @return
     */
    private Role buildRole(RoleSaveTO roleSaveTO){
        Role role = new Role();
        role.setId(StringUtils.isBlank(roleSaveTO.getId())?
                UUID.randomUUID().toString():roleSaveTO.getId());
        role.setCode(roleSaveTO.getCode());
        role.setName(roleSaveTO.getName());
        role.setDescription(roleSaveTO.getDescription());
        return role;
    }

    /**
     * 封装保存功能的绑定数据实体类
     * @param roleSaveTO
     * @return
     */
    private List<FunctionRole> buildFunctionRole(RoleSaveTO roleSaveTO){
        if (CollectionUtils.isEmpty(roleSaveTO.getFunctionIds())){
            return new ArrayList<>();
        }
        List<FunctionRole> functionRoles = new ArrayList<>();
        for (String functionId : roleSaveTO.getFunctionIds()){
            FunctionRole functionRole = new FunctionRole();
            functionRole.setFunctionId(functionId);
            functionRole.setRoleId(roleSaveTO.getId());
            functionRoles.add(functionRole);
        }
        return functionRoles;
    }
}

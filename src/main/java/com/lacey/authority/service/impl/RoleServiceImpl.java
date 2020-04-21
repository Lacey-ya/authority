package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.Pagination;
import com.lacey.authority.entity.vo.RoleListVO;
import com.lacey.authority.mapper.RoleMapper;
import com.lacey.authority.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean insertRole(RoleSaveTO roleSaveTO) {


        return false;
    }

    @Override
    public CustomPage<RoleListVO> getRoleTable(int pageNum, int pageSize, String name) {
        //使用mybatis plus的page类封装分页参数--->当前页码和页码容量参数
        Page<Role> pageParam = new Page<>(pageNum, pageSize);
        //然后使用mybatis plus的分页查询方法查询数据
        IPage<Role> pageData;
        if (StringUtils.isBlank(name)){
            pageData = roleMapper.selectPage(pageParam, new QueryWrapper<Role>());
        } else {
            pageData = roleMapper.selectPage(pageParam, new QueryWrapper<Role>().like("name", name));
        }
        //然后将分页数据拿出来
        List<Role> roles = pageData.getRecords();
        List<RoleListVO> roleListVOS = new ArrayList<>();
        for (Role role : roles){
            RoleListVO roleListVO = new RoleListVO();
            roleListVO.setId(role.getId());
            roleListVO.setCode(role.getCode());
            roleListVO.setName(role.getName());
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
        return null;
    }


    private FunctionRole buildFunctionRole(RoleSaveTO roleSaveTO){
        return null;
    }

}

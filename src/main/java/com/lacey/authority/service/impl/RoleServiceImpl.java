package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.mapper.RoleMapper;
import com.lacey.authority.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean insertRole(RoleSaveTO roleSaveTO) {


        return false;
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

package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.to.RoleSaveTO;

public interface RoleService extends IService<Role> {

    /**
     * 新增角色以及和功能的绑定数据
     * @param roleSaveTO
     * @return
     */
    boolean insertRole(RoleSaveTO roleSaveTO);

}

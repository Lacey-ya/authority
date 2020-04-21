package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.RoleListVO;

public interface RoleService extends IService<Role> {

    /**
     * 新增角色以及和功能的绑定数据
     * @param roleSaveTO
     * @return
     */
    boolean insertRole(RoleSaveTO roleSaveTO);

    /**
     * 获取角色分页列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    CustomPage<RoleListVO> getRoleTable(int pageNum, int pageSize, String name);
}

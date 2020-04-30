package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Role;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.RoleListVO;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * 获取角色分页列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    CustomPage<RoleListVO> getRoleTable(int pageNum, int pageSize, String name);

    /**
     * 新增角色以及和功能的绑定数据
     * @param roleSaveTO
     * @return
     */
    boolean insertRole(RoleSaveTO roleSaveTO);

    /**
     * 修改角色数据以及绑定的功能数据
     * @param roleSaveTO
     * @return
     */
    boolean updateRole(RoleSaveTO roleSaveTO);

    /**
     * 删除角色数据以及绑定的功能和用户数据
     * @param id
     * @return
     */
    boolean deleteRole(String id);

    /**
     * 根据id获取角色详情数据
     * @param id
     * @return
     */
    RoleSaveTO getRoleDetailListById(String id);

}

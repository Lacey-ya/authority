package com.lacey.authority.entity.vo;

import java.util.List;

/**
 * @ClassName UserRoleUpVO
 * @Description TODO
 * @Author Lacey
 * @Date 2020-06-04 10:12
 */
public class UserRoleUpVO {

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 要新增绑定的角色id集合
     */
    private List<String> addRoles;
    /**
     *要删除绑定的角色id集合
     */
    private List<String> deleteRoles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddRoles() {
        return addRoles;
    }

    public void setAddRoles(List<String> addRoles) {
        this.addRoles = addRoles;
    }

    public List<String> getDeleteRoles() {
        return deleteRoles;
    }

    public void setDeleteRoles(List<String> deleteRoles) {
        this.deleteRoles = deleteRoles;
    }
}

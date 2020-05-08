package com.lacey.authority.entity.vo;

import java.util.List;

/**
 * @ClassName UserListVO
 * @Description 用户列表数据封装类
 * @Author Lacey
 * @Date 2020-05-06 10:38
 */
public class UserListVO {

    private String id;
    private String name;
    private List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

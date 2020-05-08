package com.lacey.authority.entity.to;

import java.util.List;

/**
 * @ClassName UserSaveTo
 * @Description 新增用户数据
 * @Author Lacey
 * @Date 2020-05-07 14:17
 */
public class UserSaveTo {

    private String name;
    private List<String> addRoles;

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
}

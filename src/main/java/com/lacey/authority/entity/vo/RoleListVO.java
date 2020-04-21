package com.lacey.authority.entity.vo;

/**
 * @ClassName RoleListVO
 * @Description 角色列表数据封装类
 * @Author Lacey
 * @Date 2020-04-21 16:36
 */
public class RoleListVO {
    private String id;
    private String name;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

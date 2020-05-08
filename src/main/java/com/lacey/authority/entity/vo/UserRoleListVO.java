package com.lacey.authority.entity.vo;

/**
 * @ClassName UserRoleListVO
 * @Description 用户和角色名称映射类
 * @Author Lacey
 * @Date 2020-05-08 14:55
 *
 */
public class UserRoleListVO {
    private String userName;
    private String roleName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

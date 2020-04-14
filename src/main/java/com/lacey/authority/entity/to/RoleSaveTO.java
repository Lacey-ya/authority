package com.lacey.authority.entity.to;

/**
 * @ClassName RoleSaveTO
 * @Description 保存角色以及和功能的绑定数据
 * @Author Lacey
 * @Date 2020-04-14 13:25
 */
public class RoleSaveTO {

    private String id;
    private String name;
    private String code;
    private String description;
    private String functionIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
}

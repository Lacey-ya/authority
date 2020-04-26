package com.lacey.authority.entity.to;

import java.util.List;

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
    //这里不需要用泛型T，因为文档中标明了是String类型
    private List<String> functionIds;
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

    public List<String> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<String> functionIds) {
        this.functionIds = functionIds;
    }
}

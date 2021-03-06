package com.lacey.authority.entity.to;

/**
 * @ClassName FunctionSaveTO
 * @Description 保存功能数据参数接收类
 * @Author Lacey
 * @Date 2020-04-07 15:24
 */
public class FunctionSaveTO {

    private String id;
    private String name;
    private String code;
    private String parentId;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}

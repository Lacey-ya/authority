package com.lacey.authority.entity.vo;

import java.util.List;

/**
 * @ClassName CustomPage
 * @Description 分页列表封装类
 * @Author Lacey
 * @Date 2020-04-21 16:34
 */
public class CustomPage<T> {
    private Pagination page;
    private List<T> data;

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

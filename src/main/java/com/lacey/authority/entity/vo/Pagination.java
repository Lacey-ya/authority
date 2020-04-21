package com.lacey.authority.entity.vo;

/**
 * @ClassName Pagination
 * @Description 分页列表中的page字段封装类
 * @Author Lacey
 * @Date 2020-04-21 16:32
 */
public class Pagination {
    private int pageNum;
    private Long total;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

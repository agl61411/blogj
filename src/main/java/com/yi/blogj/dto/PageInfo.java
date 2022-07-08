package com.yi.blogj.dto;

import java.util.List;

public class PageInfo<T> {
    
    private Integer page;
    private Integer size;
    private Integer totolPage;
    private Integer total;
    private List<T> data;

    public PageInfo(Integer page, Integer size, Integer totolPage, Integer total, List<T> data) {
        this.page = page;
        this.size = size;
        this.totolPage = totolPage;
        this.total = total;
        this.data = data;
    }

    public PageInfo() {
    }

    public List<T> getList() {
        return data;
    }

    public void setList(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotolPage() {
        return totolPage;
    }

    public void setTotolPage(Integer totolPage) {
        this.totolPage = totolPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}

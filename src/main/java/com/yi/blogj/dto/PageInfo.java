package com.yi.blogj.dto;

import java.util.List;

public class PageInfo {
    
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Integer total;
    private List<Object> data;

    public PageInfo(Integer page, Integer size, Integer totalPage, Integer total, List<Object> data) {
        this.page = page;
        this.size = size;
        this.totalPage = totalPage;
        this.total = total;
        this.data = data;
    }

    public PageInfo() {
    }

    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<Object> getData() {
        return data;
    }
    public void setData(List<Object> data) {
        this.data = data;
    }


}

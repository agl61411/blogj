package com.yi.blogj.dto;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1445528204570349755L;
    private int page;
    private int size;
    private int pages;
    private int total;
    private List<T> list;

    public PageInfo(int page, int size, int pages, int total, List<T> list) {
        this.page = page;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.list = list;
    }

    public PageInfo() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

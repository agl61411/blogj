package com.yi.blogj.model;

public class Blog extends BaseInfo {
    
    private static final long serialVersionUID = -8786700512143497106L;
    
    private String title;
    private String content;
    private String tags;
    private Long accountId;

    public Blog(String title, String content, String tags, Long accountId) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.accountId = accountId;
    }

    public Blog() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

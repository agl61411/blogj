package com.yi.blogj.model;

public class Blog extends BaseInfo {
    
    private static final long serialVersionUID = -8786700512143497106L;
    
    private String title;
    private String content;
    private String tags;
    private String accountId;

    public Blog(String title, String content, String tags, String accountId) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.accountId = accountId;
    }

    public Blog() {
    }

    public String getAccountName() {
        return accountId;
    }

    public void setAccountName(String accountId) {
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

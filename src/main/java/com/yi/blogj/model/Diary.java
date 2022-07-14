package com.yi.blogj.model;

public class Diary extends BaseInfo {

    private static final long serialVersionUID = -5485748552316083897L;

    private String content;
    private Long accountId;
    
    public Diary() {
    }

    public Diary(String content, Long accountId) {
        this.content = content;
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

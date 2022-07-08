package com.yi.blogj.model;

public class Diary extends BaseInfo {

    private static final long serialVersionUID = -5485748552316083897L;

    private String content;
    private String accountName;

    public Diary(String content, String accountName) {
        this.content = content;
        this.accountName = accountName;
    }

    public Diary() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}

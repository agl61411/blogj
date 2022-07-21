package com.yi.blogj.model;

public class Diary extends BaseInfo {

    private static final long serialVersionUID = -5485748552316083897L;

    private String content;
    private String diaryTime;
    private Long accountId;
    
    public Diary(String content, String diaryTime, Long accountId) {
        this.content = content;
        this.diaryTime = diaryTime;
        this.accountId = accountId;
    }

    public Diary() {
    }

    public String getDiaryTime() {
        return diaryTime;
    }

    public void setDiaryTime(String diaryTime) {
        this.diaryTime = diaryTime;
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

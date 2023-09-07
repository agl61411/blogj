package com.yi.blogj.model;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
    private static final long serialVersionUID = 5373575360365166884L;
    private String username;
    private String accessToken;
    private String refreshToken;
    private Date issueTime;

    public UserToken() {
    }

    public UserToken(String username, String accessToken, String refreshToken, Date issueTime) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.issueTime = issueTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }
}

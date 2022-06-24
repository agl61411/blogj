package com.yi.blogj.model;

public class Account extends BaseInfo {

    private static final long serialVersionUID = -5045392447376939324L;

    private String username;
    private String password;
    private String avatar;
    private String nickname;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

}

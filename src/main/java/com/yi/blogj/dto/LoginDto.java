package com.yi.blogj.dto;

import com.yi.blogj.enums.LoginType;

public class LoginDto {
    private String username;
    private String password;
    private LoginType loginType; 
    private String captcha;

    public LoginDto(String username, String password, LoginType loginType, String captcha) {
        this.username = username;
        this.password = password;
        this.loginType = loginType;
        this.captcha = captcha;
    }

    public LoginDto() {
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.yi.blogj.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yi.blogj.dto.Result;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        Result result = null;
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result = Result.fail("用户名或密码错误，请重新输入");
        } else if (exception instanceof BadCredentialsException) {
            //密码错误
            result = Result.fail("用户名或密码错误，请重新输入");
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = Result.fail("用户名或密码错误，请重新输入");
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result = Result.fail("用户名或密码错误，请重新输入");
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = Result.fail("此账号已被锁定，请联系管理员进行处理");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = Result.fail("用户名或密码错误，请重新输入");
        }else{
            //其他错误
            result = Result.fail("登录失败");
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new Gson().toJson(result));
    }

}

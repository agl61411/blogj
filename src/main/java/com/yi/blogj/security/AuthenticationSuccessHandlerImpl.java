package com.yi.blogj.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yi.blogj.dto.Result;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new Gson().toJson(Result.ok(userDetails, "登录成功")));
    }
    
}

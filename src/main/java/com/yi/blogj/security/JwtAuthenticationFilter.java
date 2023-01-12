package com.yi.blogj.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.google.gson.Gson;
import com.yi.blogj.dao.AccountTokenDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
import com.yi.blogj.model.AccountToken;
import com.yi.blogj.utils.JwtUtil;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private AccountTokenDao accountTokenDao;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if ("/account/login".equals(request.getRequestURI()) || "/account/register".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String bearer = request.getHeader("Authorization");
        String jwt = bearer.substring("Bearer ".length());
        Claims claims = JwtUtil.parse(jwt);

        if (claims.getSubject() == null) {
            throw new BadCredentialsException("无效token");
        }

        AccountToken accountToken = accountTokenDao.findByUsername(claims.getSubject());
        if (accountToken.getAccessToken() == null) {
            throw new InsufficientAuthenticationException("请先登录");
        }
        
        if (JwtUtil.isExpire(jwt)) {
            if (jwt.equals(accountToken.getAccessToken()) && !JwtUtil.isExpire(accountToken.getRefreshToken())) {
                if ("/account/refreshToken".equals(request.getRequestURI())) {
                    chain.doFilter(request, response);
                    return;
                }

                response.setStatus(401);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(new Gson().toJson(Result.fail(40001, "登录过期")));
            } else {
                throw new InsufficientAuthenticationException("请先登录");
            }
        } else {
            Account account = new Gson().fromJson((String) claims.get("account"), Account.class);
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(account, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}

package com.yi.blogj.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.blogj.dao.UserDao;
import com.yi.blogj.dao.UserTokenDao;
import com.yi.blogj.model.User;
import com.yi.blogj.model.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.google.gson.Gson;
import com.yi.blogj.dto.Result;

import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final static Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if ("/user/login".equals(request.getRequestURI()) ||
            "/user/register".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String bearer = request.getHeader("Authorization");
        String accessToken = bearer.substring("Bearer ".length());
        if (!StringUtils.hasLength(accessToken)) {
            handleError(response, Result.fail("请先登录"));
            return;
        }
        UserToken userToken = userTokenDao.findByAccessToken(accessToken);
        if (userToken == null) {
            handleError(response, Result.fail("无效token"));
        } else {
            Date now = new Date();
            boolean accessExpire = now.getTime() - userToken.getIssueTime().getTime() > 24 * 60 * 60 * 1000;
            boolean refreshExpire = now.getTime() - userToken.getIssueTime().getTime() > 24 * 60 * 60 * 1000 * 7;
            if (accessExpire && refreshExpire) {
                handleError(response, Result.fail("无效token"));
            }
            else if (accessExpire) {
                if ("/user/refreshToken".equals(request.getRequestURI())) {
                    chain.doFilter(request, response);
                    return;
                }
                handleError(response, Result.fail(401, "token过期"));
            } else {
                User user = userDao.findUserByUsername(userToken.getUsername());
                user.setPassword(null);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        }
    }

    private void handleError(HttpServletResponse response, Result result) throws IOException {
        response.setStatus(401);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new Gson().toJson(result));
    }
}

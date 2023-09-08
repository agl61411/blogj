package com.yi.blogj.service.impl;

import com.yi.blogj.dao.UserDao;
import com.yi.blogj.dao.UserTokenDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.User;
import com.yi.blogj.model.UserToken;
import com.yi.blogj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public Result register(Map<String, String> map) {
        Result result = null;
        if (StringUtils.hasLength(map.get("username")) && StringUtils.hasLength(map.get("password")) ) {
            User unknown = userDao.findUserByUsername(map.get("username"));
            if (unknown == null) {
                User user = new User(map.get("username"), passwordEncoder.encode(map.get("password")));
                userDao.register(user);
                result = Result.ok("注册成功");
            } else {
                result = Result.fail("用户名已存在");
            }
        } else {
            result = Result.fail("请完善注册信息");
        }
        return result;
    }

    @Override
    public Result login(Map<String, String> map) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
        Authentication authenticate = authenticationManager.authenticate(token);

        Result result;
        if (authenticate == null) {
            result = Result.fail("登录失败");
        } else {
            UserToken userToken = new UserToken(map.get("username"), UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Date());
            UserToken unknown = userTokenDao.findByUsername(map.get("username"));
            if (unknown == null) {
                userTokenDao.create(userToken);
            } else {
                userTokenDao.update(userToken);
            }
            result = Result.ok(userToken);
        }
        return result;
    }

    @Override
    public Result logout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserToken logoutToken = new UserToken(user.getUsername(), null, null, null);
        userTokenDao.update(logoutToken);
        return Result.ok("登出成功");
    }

    @Override
    public Result profile() {
        return null;
    }

    @Override
    public Result refreshToken(UserToken userToken) {
        Result result;
        UserToken unknown = userTokenDao.findByUsername(userToken.getUsername());
        if (unknown == null) {
            result = Result.fail("无效token");
        }
        else if (!unknown.getRefreshToken().equals(userToken.getRefreshToken())) {
            result = Result.fail("无效token");
        }
        else {
            UserToken refreshToken = new UserToken(userToken.getUsername(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Date());
            userTokenDao.update(refreshToken);
            result = Result.ok(refreshToken);
        }
        return result;
    }

}

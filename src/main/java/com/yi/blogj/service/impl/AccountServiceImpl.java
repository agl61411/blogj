package com.yi.blogj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.AccountDao;
import com.yi.blogj.dao.AccountTokenDao;
import com.yi.blogj.dto.LoginDto;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
import com.yi.blogj.model.AccountToken;
import com.yi.blogj.service.AccountService;
import com.yi.blogj.utils.JwtUtil;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountTokenDao accountTokenDao;

    @Override
    public Result register(Account account) {
        Result result = null;
        if (account.getUsername() == null || account.getUsername().isEmpty() 
            || account.getPassword() == null || account.getPassword().isEmpty()
            || account.getNickname() == null || account.getNickname().isEmpty()) {
            result = Result.fail("请完善注册信息");
        } else {
            Account find = accountDao.findAccountByUsername(account.getUsername());
            if (find == null) {
                account.setPassword(passwordEncoder.encode(account.getPassword()));

                accountDao.register(account);
                result = Result.ok("注册成功");
            } else {
                result = Result.fail("用户名已存在");
            }
        }
        return result;
    }

    @Override
    public Result login(LoginDto login) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);

        Result result = null;
        if (authenticate == null) {
            result = Result.fail("登录失败");
        }

        Account account = accountDao.findAccountByUsername(login.getUsername());
        account.setPassword(null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        AccountToken accountToken = JwtUtil.create(login.getUsername(), map, null);
        
        AccountToken aToken = accountTokenDao.findByUsername(login.getUsername());
        if (aToken == null) {
            accountTokenDao.create(accountToken);
        } else {
            accountTokenDao.update(accountToken);
        }
        
        accountToken.setId(null);
        accountToken.setUsername(null);

        result = Result.ok(accountToken, "登录成功");
        return result;
    }

    @Override
    public Result logout() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountTokenDao.update(new AccountToken(null, null, username));
        return Result.ok("登出成功");
    }
    
}

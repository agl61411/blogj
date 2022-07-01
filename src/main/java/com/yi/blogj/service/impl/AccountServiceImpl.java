package com.yi.blogj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.AccountDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
import com.yi.blogj.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDao accountDao;

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

    
}

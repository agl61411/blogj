package com.yi.blogj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.AccountDao;
import com.yi.blogj.dto.LoginDto;
import com.yi.blogj.dto.Result;
import com.yi.blogj.enums.LoginType;
import com.yi.blogj.model.Account;
import com.yi.blogj.service.AccountService;
import com.yi.blogj.utils.SHAUtil;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Result login(LoginDto login) {
        Result result = null;
        if (LoginType.PASSWORD.equals(login.getLoginType())) {
            Account account = accountDao.findAccountByUsername(login.getUsername());
            if (account == null) {
                result = Result.fail("账号或者密码错误，请重新输入");
            }
            try {
                if (account.getPassword().equals(SHAUtil.SHA256(login.getPassword()))) {
                    result = Result.ok("登录成功");
                } else {
                    result = Result.fail("账号或者密码错误，请重新输入");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = Result.fail("服务器错误，请稍后再试");
            }
        } else if (LoginType.CAPTCHA.equals(login.getLoginType())) {

        }
        return result;
    }

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
                try {
                    account.setPassword(SHAUtil.SHA256(account.getPassword()));

                    accountDao.register(account);
                    result = Result.ok("注册成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    result = Result.fail("服务器错误，请稍后再试");
                }
            } else {
                result = Result.fail("用户名已存在");
            }
        }
        return result;
    }

    
}

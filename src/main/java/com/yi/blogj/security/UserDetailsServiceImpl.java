package com.yi.blogj.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.yi.blogj.dao.AccountDao;
import com.yi.blogj.model.Account;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        Account account = accountDao.findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误，请重新输入");
        }

        return new User(account.getUsername(), account.getPassword(), true, true, true, true, Collections.emptyList());
    }
    
}

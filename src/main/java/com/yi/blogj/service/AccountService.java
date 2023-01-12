package com.yi.blogj.service;

import com.yi.blogj.dto.LoginDto;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;

public interface AccountService {

    Result register(Account account);

    Result login(LoginDto login);

    Result logout();

    Result profile();

    Result refreshToken(String jwt);
    
}

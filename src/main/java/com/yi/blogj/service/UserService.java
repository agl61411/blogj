package com.yi.blogj.service;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.UserToken;

import java.util.Map;

public interface UserService {

    Result register(Map<String, String> map);

    Result login(Map<String, String> map);

    Result logout();

    Result profile();

    Result refreshToken(UserToken userToken);
    
}

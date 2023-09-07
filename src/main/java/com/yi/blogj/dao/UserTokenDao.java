package com.yi.blogj.dao;

import com.yi.blogj.model.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenDao {

    UserToken findByUsername(String username);

    void create(UserToken userToken);

    void update(UserToken userToken);

    UserToken findByAccessToken(String accessToken);
}

package com.yi.blogj.dao;

import org.apache.ibatis.annotations.Mapper;

import com.yi.blogj.model.AccountToken;

@Mapper
public interface AccountTokenDao {

    AccountToken findByUsername(String username);

    void create(AccountToken accountToken);

    void update(AccountToken accountToken);
    
}

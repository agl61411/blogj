package com.yi.blogj.dao;

import com.yi.blogj.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findUserByUsername(String username);
    
    void register(User user);
    
}

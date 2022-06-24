package com.yi.blogj.dao;

import org.apache.ibatis.annotations.Mapper;

import com.yi.blogj.model.Account;

@Mapper
public interface AccountDao {

    Account findAccountByUsername(String username);
    
    Account findAccountById(Long id);

    void register(Account account);
    
}

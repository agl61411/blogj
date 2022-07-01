package com.yi.blogj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
import com.yi.blogj.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        return accountService.register(account);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.ok("testtest");
    }
}   

package com.yi.blogj.controller;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.User;
import com.yi.blogj.model.UserToken;
import com.yi.blogj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        return userService.login(map);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> map) {
        return userService.register(map);
    }

    @PostMapping("/refreshToken")
    public Result refreshToken(@RequestBody UserToken userToken) {
        return userService.refreshToken(userToken);
    }

    @GetMapping("/profile")
    public Result profile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.ok(user);
    }

    @PostMapping("/logout")
    public Result logout() {
        return userService.logout();
    }
}   

package com.yi.blogj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Blog;
import com.yi.blogj.service.BlogService;

@RequestMapping("/blog")
@RestController
public class BlogController {
    
    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public Result create(@RequestBody Blog blog) {
        return blogService.create(blog);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return blogService.delete(id);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody Blog blog) {
        return blogService.update(id, blog);
    }

    @GetMapping("/{id}")
    public Result getBlog(@PathVariable("id") Long id) {
        return blogService.getBlog(id);
    }

    @PostMapping
    public Result search(@RequestBody Map<String, Object> map) {
        return blogService.search(map);
    }
}

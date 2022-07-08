package com.yi.blogj.service;

import java.util.Map;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Blog;

public interface BlogService {

    Result create(Blog blog);

    Result delete(Long id);

    Result update(Long id, Blog blog);

    Result getBlog(Long id);

    Result search(Map<String, Object> map);
    
}

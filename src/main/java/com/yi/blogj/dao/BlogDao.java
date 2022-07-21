package com.yi.blogj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yi.blogj.model.Blog;

@Mapper
public interface BlogDao {

    void create(Blog blog);

    Blog findBlogById(Long id, Long accountId);

    void deleteById(Long id);

    void update(Blog blog);

    List<List<Object>> search(Map<String, Object> map);
    
}

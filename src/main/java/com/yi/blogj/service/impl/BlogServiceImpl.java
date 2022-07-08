package com.yi.blogj.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.BlogDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Blog;
import com.yi.blogj.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Result create(Blog blog) {
        blogDao.create(blog);
        return Result.ok("创建成功");
    }

    @Override
    public Result delete(Long id) {
        Blog blog = blogDao.findBlogById(id);
        Result result = null;
        if (blog == null) {
            result = Result.fail("blog已删除");
        } else {
            blogDao.deleteById(id);
            result = Result.ok("删除成功");
        }
        return result;
    }

    @Override
    public Result update(Long id, Blog blog) {
        Blog findBlog = blogDao.findBlogById(id);
        Result result = null;
        if (findBlog == null) {
            result = Result.fail("blog不存在");
        } else {
            blogDao.update(id, blog);
            result = Result.ok("更新成功");
        }
        return result;
    }

    @Override
    public Result getBlog(Long id) {
        return Result.ok(blogDao.findBlogById(id));
    }

    @Override
    public Result search(Map<String, Object> map) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.put("accountName", username);
        
        List<List<Object>> list = blogDao.search(map);
        return null;
    }
    
}

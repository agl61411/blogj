package com.yi.blogj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.BlogDao;
import com.yi.blogj.dto.PageInfo;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
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
            blog.setId(id);
            blogDao.update(blog);
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
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int page = map.get("page") != null ? (int) map.get("page") : 1;
        int size = map.get("size") != null ? (int) map.get("size") : 10;
        
        Map<String, Object> search = new HashMap<String, Object>();
        search.put("accountId", account.getId());
        search.put("page", page - 1);
        search.put("size", size);
        List<List<Object>> list = blogDao.search(search);

        PageInfo pageInfo = new PageInfo();
        Integer total =  (Integer) list.get(1).get(0);
        pageInfo.setData(list.get(0));
        pageInfo.setTotal(total);
        pageInfo.setPage(page);
        pageInfo.setSize(size);
        pageInfo.setTotolPage((int) Math.ceil(total * 1.0 / size));

        return Result.ok(pageInfo);
    }
    
}

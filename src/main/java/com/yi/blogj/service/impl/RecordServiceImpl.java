package com.yi.blogj.service.impl;

import com.yi.blogj.dao.RecordDao;
import com.yi.blogj.dto.PageInfo;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Record;
import com.yi.blogj.model.User;
import com.yi.blogj.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {
    private final static Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private RecordDao recordDao;
    @Override
    public Result save(Record record) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        record.setUserId(user.getId());
        if (record.getId() != null) {
            recordDao.update(record);
        } else {
            recordDao.create(record);
        }
        return Result.ok();
    }

    @Override
    public Result findByTime(Date recordTime) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Record record = recordDao.findByTime(user.getId(), recordTime);
        return Result.ok(record);
    }

    @Override
    public Result search(Map<String, Object> map) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Result result = Result.fail("参数错误");
        Date start = null;
        Date end = null;
        int page = 0;
        int size = 0;
        try {
            start = format.parse((String) map.get("start"));
            end = format.parse((String) map.get("end"));
            page = (int) map.get("page");
            size = (int) map.get("size");
        } catch (Exception e) {
            log.warn("参数错误：" + map.toString());
        }
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<?> res = recordDao.search(user.getId(), start, end, page - 1, size);
        List<Record> list = (List<Record>) res.get(0);
        int total = ((List<Integer>) res.get(1)).get(0);
        int pages = (total / size) + (total % size == 0 ? 0 : 1);
        result = Result.ok(new PageInfo<>(page, size, pages, total, list));
        return result;
    }
}

package com.yi.blogj.dao;

import com.yi.blogj.model.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecordDao {
    void create(Record record);
    void update(Record record);
    Record findByTime(Integer userId, Date recordTime);
    List<?> search(int userId, Date start, Date end, Integer page, Integer size);
}

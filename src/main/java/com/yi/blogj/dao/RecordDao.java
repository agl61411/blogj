package com.yi.blogj.dao;

import com.yi.blogj.model.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface RecordDao {
    Record findRecordByDay(Date recordTime);
    void create(Record record);
    void update(Record record);
}

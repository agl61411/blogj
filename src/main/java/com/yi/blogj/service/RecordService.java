package com.yi.blogj.service;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Record;

import java.util.Date;
import java.util.Map;

public interface RecordService {
    Result save(Record record);
    Result findByTime(Date recordTime);
    Result search(Map<String, Object> map);
}

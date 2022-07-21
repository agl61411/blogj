package com.yi.blogj.dao;

import org.apache.ibatis.annotations.Mapper;

import com.yi.blogj.model.Diary;

@Mapper
public interface DiaryDao {

    Diary findDiaryById(Long id, Long accountId);

    void create(Diary diary);

    void update(Diary diary);

    void deleteById(Long id, Long accountId);

    Diary findDiaryByTime(String diaryTime, Long accountId);
    
}

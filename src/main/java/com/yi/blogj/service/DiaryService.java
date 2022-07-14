package com.yi.blogj.service;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Diary;

public interface DiaryService {

    Result getDiary(String diaryTime);

    Result create(Diary diary);

    Result update(Long id, Diary diary);

    Result delete(Long id);
    
}

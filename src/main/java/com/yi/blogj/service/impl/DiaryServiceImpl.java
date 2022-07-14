package com.yi.blogj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.DiaryDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Diary;
import com.yi.blogj.service.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryDao diaryDao;

    @Override
    public Result getDiary(String diaryTime) {
        Diary diary = diaryDao.findDiaryByTime(diaryTime);
        Result result = null;
        if (diary == null) {
            result = Result.fail("diary不存在");
        } else {
            result = Result.ok(diary);
        }
        return result;
    }

    @Override
    public Result create(Diary diary) {
        diaryDao.create(diary);
        return Result.ok("创建成功");
    }

    @Override
    public Result update(Long id, Diary diary) {
        Diary findDiary = diaryDao.findDiaryById(id);
        Result result = null;
        if (findDiary == null) {
            result = Result.fail("diary不存在");
        } else {
            diary.setId(id);
            diaryDao.update(diary);
            result = Result.ok("更新成功");
        }
        return result;
    }

    @Override
    public Result delete(Long id) {
        Diary findDiary = diaryDao.findDiaryById(id);
        Result result = null;
        if (findDiary == null) {
            result = Result.fail("diary不存在");
        } else {
            diaryDao.deleteById(id);
            result = Result.ok("删除成功");
        }
        return result;
    }
    
}

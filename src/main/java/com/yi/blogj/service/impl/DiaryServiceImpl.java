package com.yi.blogj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yi.blogj.dao.DiaryDao;
import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Account;
import com.yi.blogj.model.Diary;
import com.yi.blogj.service.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryDao diaryDao;

    @Override
    public Result getDiary(String diaryTime) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Diary diary = diaryDao.findDiaryByTime(diaryTime, account.getId());
        return Result.ok(diary);
    }

    @Override
    public Result create(Diary diary) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        diary.setAccountId(account.getId());
        diaryDao.create(diary);
        return Result.ok("创建成功");
    }

    @Override
    public Result update(Long id, Diary diary) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Diary findDiary = diaryDao.findDiaryById(id, account.getId());
        Result result = null;
        if (findDiary == null) {
            result = Result.fail("diary不存在");
        } else {
            diary.setId(id);
            diary.setAccountId(account.getId());
            diaryDao.update(diary);
            result = Result.ok("更新成功");
        }
        return result;
    }

    @Override
    public Result delete(Long id) {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Diary findDiary = diaryDao.findDiaryById(id, account.getId());
        Result result = null;
        if (findDiary == null) {
            result = Result.fail("diary不存在");
        } else {
            diaryDao.deleteById(id, account.getId());
            result = Result.ok("删除成功");
        }
        return result;
    }
    
}

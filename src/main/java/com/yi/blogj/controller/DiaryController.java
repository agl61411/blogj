package com.yi.blogj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Diary;
import com.yi.blogj.service.DiaryService;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    
    @Autowired
    private DiaryService diaryService;

    @GetMapping("/{diaryTime}")
    public Result getDiary(@PathVariable String diaryTime) {
        return diaryService.getDiary(diaryTime);
    }

    @PostMapping("/create")
    public Result create(@RequestBody Diary diary) {
        return diaryService.create(diary);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Diary diary) {
        return diaryService.update(id, diary);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return diaryService.delete(id);
    }
}

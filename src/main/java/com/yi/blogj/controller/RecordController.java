package com.yi.blogj.controller;

import com.yi.blogj.dto.Result;
import com.yi.blogj.model.Record;
import com.yi.blogj.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        return recordService.save(record);
    }

    @GetMapping("/{recordTime}")
    public Result getRecord(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date recordTime) {
        return recordService.findByTime(recordTime);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Map<String, Object> map) {
        return recordService.search(map);
    }
}

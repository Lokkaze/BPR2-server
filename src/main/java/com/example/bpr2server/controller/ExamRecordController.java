package com.example.bpr2server.controller;

import com.example.bpr2server.model.ExamRecords;
import com.example.bpr2server.service.ExamRecordsService;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class ExamRecordController {
    @Autowired
    ExamRecordsService examRecordsService;

    @GetMapping("")
    public Result fetchExamRecord(int userId, int examId) {
        ExamRecords examRecords = examRecordsService.fetchExamRecord(userId, examId);
        return Result.ok().data("record", examRecords);
    }

    @PostMapping("/update")
    public Result updateExamRecord(@RequestBody ExamRecords examRecords) {
        String result = examRecordsService.updateExamRecord(examRecords);
        return Result.ok().data("result", result);
    }
}

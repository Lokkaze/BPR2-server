package com.example.bpr2server.controller;

import com.example.bpr2server.model.ExamCheat;
import com.example.bpr2server.model.ExamRecords;
import com.example.bpr2server.service.ExamInfoService;
import com.example.bpr2server.service.ExamRecordsService;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class ExamRecordController {
    @Autowired
    ExamRecordsService examRecordsService;
    @Autowired
    ExamInfoService examInfoService;

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

    @PostMapping("/upload/photo")
    public Result uploadPhoto(int userId, MultipartFile file){
        examRecordsService.uploadPhoto(userId, file);

        return Result.ok();
    }

    @PostMapping("/cheat")
    public Result cheatDetected(@RequestBody ExamCheat examCheat) {
        String result = examRecordsService.cheatDetected(examCheat);
        return Result.ok().data("result", result);
    }

    @GetMapping("/cheat/list")
    public Result fetchCheatList(int examId) {
        List<ExamCheat> list = examRecordsService.fetchCheatList(examId);
        return Result.ok().data("list", list);
    }

    @DeleteMapping("/cheat/delete")
    public Result deleteCheat(int userId, int examId, String timestamp) {
        String result = examRecordsService.deleteCheat(userId, examId, timestamp);
        return Result.ok().data("result", result);
    }

    @GetMapping("/cheat/confirm")
    public Result confirmCheat(int userId, int examId) {
        String result = examInfoService.confirmCheat(userId, examId);
        return Result.ok().data("result", result);
    }

}

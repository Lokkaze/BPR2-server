package com.example.bpr2server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bpr2server.model.ExamInfo;
import com.example.bpr2server.model.UserExam;
import com.example.bpr2server.service.ExamInfoService;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/detail")
@CrossOrigin
public class ExamInfoController {
    @Autowired
    ExamInfoService examInfoService;

    @GetMapping("")
    public Result fetchExamDetail(int examId){
        ExamInfo info = examInfoService.fetchExamDetail(examId);
        List<UserExam> userExamList = examInfoService.fetchUserExamList(examId);
        return Result.ok().data("info", info).data("userExam", userExamList);
    }

    @PostMapping("/edit")
    public Result updateExamDetail(@RequestBody ExamInfo examInfo){
        String result = examInfoService.updateExamDetail(examInfo);
        if (result == "Update success"){
            return Result.ok().data("result", result);
        }
        else return Result.error().data("result", result);
    }

    @GetMapping("/studentList")
    public Result fetchStudentList(String username, int page, int limit){
        IPage list = examInfoService.fetchStudents(username, page, limit);
        return Result.ok().data("list", list);
    }

}

package com.example.bpr2server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bpr2server.model.Exam;
import com.example.bpr2server.model.ExamInfo;
import com.example.bpr2server.service.ExamService;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@CrossOrigin
public class ExamController {
    @Autowired
    ExamService examService;

    @GetMapping("/list")
    public Result fetchExam(String username, int page, int limit, String title, String status){
        IPage list = examService.fetchExam(username, page, limit, title, status);
        return Result.ok().data("list", list);
    }

    @PostMapping("/create")
    public Result createExam(@RequestBody Exam exam){
        String result = examService.addExam(exam);
        if (result == "Add success"){
            return Result.ok().data("result", result);
        }
        else return Result.error().data("result", result);
    }

    @PostMapping("/update")
    public Result updateExam(@RequestBody Exam exam){
        Exam updatedExam = examService.updateExam(exam);
        System.out.println("updated exam: " + updatedExam);
        return Result.ok();
    }
    @DeleteMapping("/delete")
    public Result deleteExam(int examId){
        String result = examService.deleteExam(examId);
        System.out.println(result);
        return Result.ok();
    }
}

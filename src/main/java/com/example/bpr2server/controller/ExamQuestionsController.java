package com.example.bpr2server.controller;

import com.example.bpr2server.model.ExamQuestions;
import com.example.bpr2server.service.ExamQuestionsService;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/detail/questions")
@CrossOrigin
public class ExamQuestionsController {
    @Autowired
    ExamQuestionsService examQuestionsService;

    @GetMapping("")
    public Result fetchQuestions(int examId){
        List<ExamQuestions> examQuestionsList = examQuestionsService.fetchExamQuestions(examId);
        return Result.ok().data("questions", examQuestionsList);
    }

    @PostMapping("/edit")
    public Result updateQuestions(int examId, @RequestBody List<ExamQuestions> examQuestions){
        String result = examQuestionsService.updateExamQuestions(examId, examQuestions);
        if (result == "Update success"){
            return Result.ok().data("result", result);
        }
        else return Result.error().data("result", result);
    }
}

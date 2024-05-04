package com.example.bpr2server.service;

import com.example.bpr2server.model.ExamQuestions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamQuestionsService {
    List<ExamQuestions> fetchExamQuestions(int examId);
    String updateExamQuestions(int examId, List<ExamQuestions> examQuestions);
}

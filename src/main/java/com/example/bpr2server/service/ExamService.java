package com.example.bpr2server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bpr2server.model.Exam;
import com.example.bpr2server.model.ExamInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {
    IPage fetchExam(String username, int page, int limit);
    String addExam(Exam exam);
    Exam updateExam(Exam exam);
    String deleteExam(int examId);
    ExamInfo fetchExamDetail(int examId);
}

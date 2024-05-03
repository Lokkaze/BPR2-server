package com.example.bpr2server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bpr2server.model.ExamInfo;
import org.springframework.stereotype.Service;

@Service
public interface ExamInfoService {
    ExamInfo fetchExamDetail(int examId);
    String updateExamDetail(ExamInfo examInfo);
    IPage fetchStudents(String username, int page, int limit);
}

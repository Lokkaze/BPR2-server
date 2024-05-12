package com.example.bpr2server.service;

import com.example.bpr2server.model.ExamRecords;
import org.springframework.stereotype.Service;

@Service
public interface ExamRecordsService {
    ExamRecords fetchExamRecord(int userId, int examId);
    String updateExamRecord(ExamRecords examRecords);
}

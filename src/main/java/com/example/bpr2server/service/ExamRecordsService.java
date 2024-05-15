package com.example.bpr2server.service;

import com.example.bpr2server.model.ExamCheat;
import com.example.bpr2server.model.ExamRecords;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public interface ExamRecordsService {
    ExamRecords fetchExamRecord(int userId, int examId);
    String updateExamRecord(ExamRecords examRecords);
    String uploadPhoto(int userId, MultipartFile photo);
    String cheatDetected(ExamCheat examCheat);
    List<ExamCheat> fetchCheatList(int examId);
    String deleteCheat(int userId, int examId, String timestamp);
}

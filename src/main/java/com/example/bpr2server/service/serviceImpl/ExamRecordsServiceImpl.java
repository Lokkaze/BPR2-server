package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bpr2server.mapper.ExamCheatMapper;
import com.example.bpr2server.mapper.ExamRecordsMapper;
import com.example.bpr2server.mapper.UserExamMapper;
import com.example.bpr2server.model.ExamCheat;
import com.example.bpr2server.model.ExamRecords;
import com.example.bpr2server.model.UserExam;
import com.example.bpr2server.service.ExamRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("ExamRecordsService")
public class ExamRecordsServiceImpl implements ExamRecordsService {
    @Autowired
    ExamRecordsMapper examRecordsMapper;
    @Autowired
    ExamCheatMapper examCheatMapper;
    @Autowired
    UserExamMapper userExamMapper;

    @Override
    public ExamRecords fetchExamRecord(int userId, int examId) {
        QueryWrapper<ExamRecords> examRecordsQueryWrapper = new QueryWrapper();
        examRecordsQueryWrapper.eq("user_id", userId);
        examRecordsQueryWrapper.eq("exam_id", examId);
        return examRecordsMapper.selectOne(examRecordsQueryWrapper);
    }

    @Override
    public String updateExamRecord(ExamRecords examRecords) {
        QueryWrapper<ExamRecords> examRecordsQueryWrapper = new QueryWrapper();
        examRecordsQueryWrapper.eq("user_id", examRecords.getUserId());
        examRecordsQueryWrapper.eq("exam_id", examRecords.getExamId());
        int update = examRecordsMapper.update(examRecords, examRecordsQueryWrapper);
        if (update != 0) {
            return "Update success";
        }
        else if (update == 0) {
            int insert = examRecordsMapper.insert(examRecords);
            return "Insert success";
        }
        else return "Insert or update failure";
    }

    @Override
    public String uploadPhoto(int userId, MultipartFile photo) {
        String dirPath = System.getProperty("user.dir");
        File destFile = new File(dirPath + "/src/main/resources/static/upload/" + userId + ".jpg");
        if (!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdir();
        }
        try {
            photo.transferTo(destFile);
            return "Upload success";
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String cheatDetected(ExamCheat examCheat) {
        int update = 0;
        QueryWrapper<UserExam> userExamQueryWrapper = new QueryWrapper();
        userExamQueryWrapper.eq("user_id", examCheat.getUserId());
        userExamQueryWrapper.eq("exam_id", examCheat.getExamId());
        String status = userExamMapper.selectOne(userExamQueryWrapper).getUserExamStatus();
        System.out.println("==============================================="+status);
        if (!status.equals("cheating detected")) {
            UserExam suspectedStudent = new UserExam(examCheat.getExamId(), examCheat.getUserId(), "suspected of cheating");
            update = userExamMapper.update(suspectedStudent, userExamQueryWrapper);
        }
        int insert = examCheatMapper.insert(examCheat);

        if (insert > 0 && update > 0) {
            return "Set to suspected of cheating";
        } else if (update == 0) {
            return "Cheat already confirmed";
        }
        return "Set cheating status failure";
    }

    @Override
    public List<ExamCheat> fetchCheatList(int examId) {
        List<ExamCheat> list = examCheatMapper.getExamCheatList(examId);
        return list;
    }

    @Override
    public String deleteCheat(int userId, int examId, String timestamp) {
        QueryWrapper<ExamCheat> examCheatQueryWrapper = new QueryWrapper();
        examCheatQueryWrapper.eq("user_id", userId);
        examCheatQueryWrapper.eq("exam_id", examId);
        examCheatQueryWrapper.eq("timestamp", timestamp);

        int update = examCheatMapper.delete(examCheatQueryWrapper);
        if (update > 0) {
            return "Delete success";
        } else return "Delete failure";
    }
}

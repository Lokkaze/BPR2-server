package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bpr2server.mapper.ExamRecordsMapper;
import com.example.bpr2server.model.ExamRecords;
import com.example.bpr2server.service.ExamRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ExamRecordsService")
public class ExamRecordsServiceImpl implements ExamRecordsService {
    @Autowired
    ExamRecordsMapper examRecordsMapper;

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
}

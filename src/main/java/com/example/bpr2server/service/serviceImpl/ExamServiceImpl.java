package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bpr2server.mapper.ExamMapper;
import com.example.bpr2server.model.Exam;
import com.example.bpr2server.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExamService")
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Override
    public IPage fetchExam(String username, int page, int limit) {

        Page<Exam> examPage = new Page<>(page, limit);

        QueryWrapper<Exam> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        IPage iPage = examMapper.selectPage(examPage, queryWrapper);
        return iPage;
    }

    @Override
    public String addExam(Exam exam) {
        int i = examMapper.insert(exam);
        System.out.println("Adding exam: " + exam);
        if (i > 0){
            return "Add success";
        }
        else return "Add failure";
    }

    @Override
    public Exam updateExam(Exam exam) {
        return null;
    }

    @Override
    public String deleteExam(int examId) {
        QueryWrapper<Exam> queryWrapper = new QueryWrapper();
        queryWrapper.eq("exam_id", examId);
        int i = examMapper.delete(queryWrapper);
        if (i > 0){
            return "Delete success";
        }
        return "Delete failure";
    }
}

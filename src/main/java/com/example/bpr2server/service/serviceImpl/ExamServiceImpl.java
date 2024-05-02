package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bpr2server.mapper.ExamInfoMapper;
import com.example.bpr2server.mapper.ExamMapper;
import com.example.bpr2server.mapper.UserExamMapper;
import com.example.bpr2server.mapper.UserMapper;
import com.example.bpr2server.model.Exam;
import com.example.bpr2server.model.ExamInfo;
import com.example.bpr2server.model.User;
import com.example.bpr2server.model.UserExam;
import com.example.bpr2server.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ExamService")
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamInfoMapper examInfoMapper;

    @Autowired
    private UserExamMapper userExamMapper;

    @Autowired
    private UserMapper userMapper;

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
        int i = examMapper.insertExam(exam);
        System.out.println("Adding exam: " + exam);
        if (i > 0){
            ExamInfo info = new ExamInfo(exam.getExamId());
            examInfoMapper.insert(info);
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

    @Override
    public ExamInfo fetchExamDetail(int examId) {
        List<UserExam> userExamList = userExamMapper.selectByExamId(examId);
        List<User> users = fetchStudentList(userExamList);
        ExamInfo info = examInfoMapper.getExamInfo(examId);
        info.setUsers(users);
        return info;
    }

    List<User> fetchStudentList(List<UserExam> userExamList){
        List<User> userList = new ArrayList<>();
        for (UserExam item : userExamList){
            userList.add(userMapper.selectByUserId(item.getUserId()));
        }
        return userList;
    }
}

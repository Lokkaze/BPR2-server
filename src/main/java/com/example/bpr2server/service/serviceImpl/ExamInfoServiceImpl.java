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
import com.example.bpr2server.service.ExamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ExamInfoService")
public class ExamInfoServiceImpl implements ExamInfoService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamInfoMapper examInfoMapper;

    @Autowired
    private UserExamMapper userExamMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public String updateExamDetail(ExamInfo examInfo) {

        //examInfoUpdate
        QueryWrapper<ExamInfo> examInfoQueryWrapper = new QueryWrapper();
        examInfoQueryWrapper.eq("exam_id", examInfo.getExamId());
        int examInfoUpdate = examInfoMapper.update(examInfo, examInfoQueryWrapper);

        //examUpdate
        QueryWrapper<Exam> examQueryWrapper = new QueryWrapper();
        examQueryWrapper.eq("exam_id", examInfo.getExam().getExamId());
        int examUpdate = examMapper.update(examInfo.getExam(), examQueryWrapper);

        //userExamUpdate
        int userExamUpdate = 0;
        QueryWrapper<UserExam> userExamQueryWrapper = new QueryWrapper();
        userExamQueryWrapper.eq("exam_id", examInfo.getExamId());

        List<UserExam> newRelation = new ArrayList<>();
        for (User item: examInfo.getUsers()){
            UserExam newItem = new UserExam(examInfo.getExamId(), item.getUserId());
            newRelation.add(newItem);
        }
        List<UserExam> originalRelation = userExamMapper.selectByExamId(examInfo.getExamId());

        List<UserExam> toAdd = new ArrayList<>();
        for (UserExam item: newRelation){
            if (!originalRelation.contains(item)){
                toAdd.add(item);
            }
        }

        List<UserExam> toDelete = new ArrayList<>();
        for (UserExam item: originalRelation){
            if (!newRelation.contains(item)){
                toDelete.add(item);
            }
        }

        for (UserExam item: toAdd){
            userExamMapper.insert(item);
            userExamUpdate++;
        }

        for (UserExam item: toDelete){
            QueryWrapper<UserExam> toDelUserExamQueryWrapper = new QueryWrapper();
            toDelUserExamQueryWrapper.eq("exam_id", item.getExamId());
            toDelUserExamQueryWrapper.eq("user_id", item.getUserId());
            userExamMapper.delete(toDelUserExamQueryWrapper);
            userExamUpdate++;
        }

        if (examInfoUpdate > 0 || examUpdate > 0 || userExamUpdate > 0){
            return "Update success";
        }
        else return "Update failure";
    }

    @Override
    public IPage fetchStudents(String username, int page, int limit) {
        Page<User> studentPage = new Page<>(page, limit);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_teacher", false);
        queryWrapper.select(User.class, tableFieldInfo -> !tableFieldInfo.getColumn().equals("password"));
        if (username != ""){
            queryWrapper.like("username", username);
        }
        IPage iPage = userMapper.selectPage(studentPage, queryWrapper);
        return iPage;
    }


}

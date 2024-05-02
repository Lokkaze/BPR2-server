package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

public class UserExam {
    private int examId;
    private int userId;
    @TableField(exist = false)
    private List<User> users; //students

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;
import java.util.Objects;

public class UserExam {
    private int examId;
    private int userId;
    private String UserExamStatus;
    @TableField(exist = false)
    private List<User> users; //students
    @TableField(exist = false)
    private String username;

    public UserExam(int examId, int userId) {
        this.examId = examId;
        this.userId = userId;
        this.UserExamStatus = "Unsubmitted";
    }

    public UserExam(int examId, int userId, String userExamStatus) {
        this.examId = examId;
        this.userId = userId;
        this.UserExamStatus = userExamStatus;
    }
    public UserExam() {}

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

    public String getUserExamStatus() {
        return UserExamStatus;
    }

    public void setUserExamStatus(String userExamStatus) {
        UserExamStatus = userExamStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExam userExam = (UserExam) o;
        return userId == userExam.userId && examId == userExam.examId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, examId);
    }
}

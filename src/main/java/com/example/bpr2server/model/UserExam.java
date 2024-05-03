package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;
import java.util.Objects;

public class UserExam {
    private int examId;
    private int userId;
    @TableField(exist = false)
    private List<User> users; //students

    public UserExam(int examId, int userId) {
        this.examId = examId;
        this.userId = userId;
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

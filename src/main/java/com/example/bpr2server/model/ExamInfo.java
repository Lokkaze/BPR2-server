package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

public class ExamInfo {
    private int examId;

    @TableField(exist = false)
    private Exam exam;

    @TableField(exist = false)
    private List<User> users; //students

    public ExamInfo(int examId) {
        this.examId = examId;
    }

    public ExamInfo() {}

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

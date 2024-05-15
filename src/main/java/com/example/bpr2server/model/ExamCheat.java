package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

public class ExamCheat {
    private int examId;
    private int userId;
    private String timestamp;
    @TableField(exist = false)
    private String username;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.bpr2server.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;
import java.util.Objects;

public class ExamQuestions {
    private int examId;
    private int questionId;
    private String type;
    private String title;
    private int examQuestionsId;
    @TableField(exist = false)
    private List<ExamQuestionAnswers> examQuestionAnswers;

    public ExamQuestions() {
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getExamQuestionsId() {
        return examQuestionsId;
    }

    public void setExamQuestionsId(int examQuestionsId) {
        this.examQuestionsId = examQuestionsId;
    }

    public List<ExamQuestionAnswers> getExamQuestionAnswers() {
        return examQuestionAnswers;
    }

    public void setExamQuestionAnswers(List<ExamQuestionAnswers> examQuestionAnswers) {
        this.examQuestionAnswers = examQuestionAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamQuestions examQuestions = (ExamQuestions) o;
        return examQuestionsId == examQuestions.examQuestionsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(examQuestionsId);
    }
}

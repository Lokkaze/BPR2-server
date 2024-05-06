package com.example.bpr2server.model;

import java.util.Objects;

public class ExamQuestionAnswers {
    private int examQuestionAnswersId;
    private int examId;
    private int questionId;
    private String content;

    private int answerId;

    public ExamQuestionAnswers() {
    }

    public int getExamQuestionAnswersId() {
        return examQuestionAnswersId;
    }

    public void setExamQuestionAnswersId(int examQuestionAnswersId) {
        this.examQuestionAnswersId = examQuestionAnswersId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamQuestionAnswers examQuestionAnswers = (ExamQuestionAnswers) o;
        return examQuestionAnswersId == examQuestionAnswers.examQuestionAnswersId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(examQuestionAnswersId);
    }
}

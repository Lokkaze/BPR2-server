package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.ExamQuestionAnswers;
import com.example.bpr2server.model.ExamQuestions;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamQuestionsMapper extends BaseMapper<ExamQuestions> {
    @Select("SELECT * FROM exam_questions WHERE exam_id = #{examId}")
    @Results({
            @Result(column = "exam_id", property = "examId"),
            @Result(column = "question_id", property = "questionId"),
            @Result(column = "type", property = "type"),
            @Result(column = "title", property = "title")
    })
    List<ExamQuestions> getExamQuestions(int examId);

}

package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.ExamQuestionAnswers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamQuestionAnswersMapper extends BaseMapper<ExamQuestionAnswers> {
    @Select("SELECT * FROM exam_question_answers WHERE exam_id = #{examId} AND question_id = #{questionId}")
    List<ExamQuestionAnswers> selectByExamIdAndQuestionId(@Param("examId") int examId, @Param("questionId") int questionId);
}

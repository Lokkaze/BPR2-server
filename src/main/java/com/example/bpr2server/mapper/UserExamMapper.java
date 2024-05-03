package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.User;
import com.example.bpr2server.model.UserExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserExamMapper extends BaseMapper<UserExam> {
    @Select("select * from user_exam where exam_id = #{examId}")
    List<UserExam> selectByExamId(int examId);

}

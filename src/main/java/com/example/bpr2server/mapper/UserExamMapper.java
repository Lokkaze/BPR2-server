package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.User;
import com.example.bpr2server.model.UserExam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserExamMapper extends BaseMapper<UserExam> {
    @Select("select * from user_exam where exam_id = #{examId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "exam_id", property = "examId"),
            @Result(column = "user_exam_status", property = "UserExamStatus"),
            @Result(column = "user_id", property = "username", javaType = String.class,
                    one = @One(select = "com.example.bpr2server.mapper.UserMapper.getUsername"))
    })
    List<UserExam> selectByExamId(int examId);

}

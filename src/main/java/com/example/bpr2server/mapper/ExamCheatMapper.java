package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.ExamCheat;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamCheatMapper extends BaseMapper<ExamCheat> {
    @Select("select * from exam_cheat where exam_id = #{examId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "exam_id", property = "examId"),
            @Result(column = "user_id", property = "username", javaType = String.class,
                one = @One(select = "com.example.bpr2server.mapper.UserMapper.getUsername"))
    })
    List<ExamCheat> getExamCheatList(int examId);
}

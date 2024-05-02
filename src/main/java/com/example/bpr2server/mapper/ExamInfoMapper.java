package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.Exam;
import com.example.bpr2server.model.ExamInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExamInfoMapper extends BaseMapper<ExamInfo> {

    @Select("select * from exam_info where exam_id = #{examId}")
    @Results({
            @Result(column = "exam_id", property = "examId"),
            @Result(column = "exam_id", property = "exam", javaType = Exam.class,
            one = @One(select = "com.example.bpr2server.mapper.ExamMapper.selectByExamId"))
    })
    ExamInfo getExamInfo(int examId);
}

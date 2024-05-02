package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {
    @Select("select * from exam where exam_id = #{examId}")
    Exam selectByExamId(int examId);

    @Insert("insert into exam (exam_id, title, start_timestamp, end_timestamp, username, status) values (#{examId}, #{title}, #{startTimestamp}, #{endTimestamp}, #{username}, #{status})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "examId", before = false, resultType = int.class)
    int insertExam(Exam exam);
}

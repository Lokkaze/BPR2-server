package com.example.bpr2server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bpr2server.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select user_id, username, is_teacher, avatar from user where user_id = #{userId}")
    User selectByUserId(int userId);

    @Select("select username from user where user_id = #{userId}")
    String getUsername(int userId);
}

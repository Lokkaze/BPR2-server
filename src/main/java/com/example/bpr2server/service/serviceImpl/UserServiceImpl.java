package com.example.bpr2server.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bpr2server.mapper.UserMapper;
import com.example.bpr2server.model.User;
import com.example.bpr2server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List getUsers() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    @Override
    public User getUserById(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public String loginUser(String username, String password) {
        User userGet;
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        userGet = userMapper.selectOne(queryWrapper);
        System.out.println("User get: " + userGet);
        if (userGet == null){
            return "Cannot find user"; //Cannot find user
        }
        else if (password.equals(userGet.getPassword())){
            return "Login success"; //Login success
        }
        else return "Login failure"; //Login failure

    }

    @Override
    public String addUser(User newUser) {
        int i = userMapper.insert(newUser);
        System.out.println("Adding user: " + newUser);
        if (i > 0){
            return "Add success";
        }
        else return "Add failure";
    }

    @Override
    public void updateUser(User newUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",newUser.getUserId());
        int i = userMapper.update(newUser, queryWrapper);
        if (i > 0){
            System.out.println("Update success");
        }
        else System.out.println("Update failure");
    }
}
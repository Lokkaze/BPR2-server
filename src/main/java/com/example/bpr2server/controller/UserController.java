package com.example.bpr2server.controller;

import com.example.bpr2server.model.User;
import com.example.bpr2server.service.UserService;
import com.example.bpr2server.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUsers")
    public List getUsers(){
        List list = userService.getUsers();
        System.out.println(list);
        return list;
    }

    @GetMapping("/getUserById")
    public User getUser(int userId, String username){
        System.out.println(userId);
        System.out.println(username);
        return userService.getUserById(userId);
    }

    @GetMapping("/login")
    public String loginUser(String username, String password){
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return userService.loginUser(username, password);
    }

    @PostMapping("/user/add")
    public User addUser(@RequestBody User newUser){
        System.out.println(newUser);
        String result = userService.addUser(newUser);
        System.out.println(result);
        return userService.getUserById(newUser.getUserId());
    }

    @PostMapping("/user/update")
    public User updateUser(@RequestBody User newUser){
        userService.updateUser(newUser);
        return userService.getUserById(newUser.getUserId());
    }
}

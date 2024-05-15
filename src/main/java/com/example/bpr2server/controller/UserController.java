package com.example.bpr2server.controller;

import com.example.bpr2server.model.User;
import com.example.bpr2server.service.UserService;
import com.example.bpr2server.service.serviceImpl.UserServiceImpl;
import com.example.bpr2server.utils.JwtUtils;
import com.example.bpr2server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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
    public User getUser(int userId){
        System.out.println(userId);
        return userService.getUserById(userId);
    }

    @PostMapping("/login")
    public Result loginUser(@RequestBody User user){
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        String r = userService.loginUser(user.getUsername(), user.getPassword());
        System.out.println(r);
        if (r == "Login success")
        {
            String token = JwtUtils.generateToken(user.getUsername());
            return Result.ok().data("token", token);
        }
        else return Result.error();
    }

    @GetMapping("/logout")
    public Result logoutUser(){
        return Result.ok();
    }

    @GetMapping("/info")
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        User user = userService.getUserByUsername(username);
        return Result.ok().data("name", username).data("avatar", user.getAvatar()).data("isTeacher", user.getIsTeacher()).data("userId", user.getUserId());
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User newUser){
        System.out.println(newUser);
        String result = userService.addUser(newUser);
        System.out.println(result);
        return userService.getUserById(newUser.getUserId());
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User newUser){
        String result = userService.updateUser(newUser);
        return Result.ok().data("result", result);
    }

    @GetMapping("/username")
    public Result fetchUsername(int userId) {
        String username = userService.fetchUsername(userId);
        return Result.ok().data("username", username);
    }

    @PostMapping("/password")
    public Result changePassword(@RequestBody User newUser) {
        String result = userService.changePassword(newUser);
        return Result.ok().data("result", result);
    }
}

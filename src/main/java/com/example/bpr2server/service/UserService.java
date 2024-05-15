package com.example.bpr2server.service;

import com.example.bpr2server.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List getUsers();
    User getUserById(int userId);
    User getUserByUsername(String username);
    String loginUser(String username, String password);
    String addUser(User newUser);
    String updateUser(User newUser);
    String fetchUsername(int userId);
    String changePassword(User newUser);
}

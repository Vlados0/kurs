package com.example.cyber.service;

import com.example.cyber.model.User;

import java.util.List;

public interface UserService {

    void registration(String username, String password);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);
}

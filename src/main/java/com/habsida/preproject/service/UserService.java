package com.habsida.preproject.service;

import com.habsida.preproject.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
}

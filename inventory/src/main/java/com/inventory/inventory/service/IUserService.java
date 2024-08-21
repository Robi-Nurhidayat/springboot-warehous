package com.inventory.inventory.service;

import com.inventory.inventory.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User save(User user);
//    User updateUser(User user);
    boolean deleteUser(Long userId);



}

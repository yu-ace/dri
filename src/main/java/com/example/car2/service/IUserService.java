package com.example.car2.service;

import com.example.car2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    void newUser(String name,String password);
    void changePassword(String name,String Password);
    Page<User> getUserListByStatus(int status,Pageable pageable);
    void deleteUser(int id);
    User getUserById(int id);
    User getUserByName(String name);
    void activationUser(String name);
}

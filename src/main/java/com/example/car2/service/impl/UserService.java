package com.example.car2.service.impl;

import com.example.car2.dao.IUserDao;
import com.example.car2.model.User;
import com.example.car2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public void newUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void changePassword(String name, String Password) {
        User user = userDao.findByName(name);
        user.setPassword(user.getPassword());
        userDao.save(user);
    }

    @Override
    public Page<User> getUserListByStatus(int status, Pageable pageable) {
        return userDao.findByIsDelete(status,pageable);
    }

    @Override
    public void deleteUser(int id) {
        User user = userDao.findById(id);
        user.setIsDelete(1);
        userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void activationUser(String name) {
        User user = userDao.findByName(name);
        user.setIsDelete(0);
        userDao.save(user);
    }
}

package com.backend.service.impl;

import com.backend.dao.UserDao;
import com.backend.models.table.User;
import com.backend.service.UserService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Gson gson;

    public UserServiceImpl(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }


    @Override
    public User findByUserId(String userId) {
        LoggerService.info("[UserService | findByUserId] Finding user by id {}", userId);
        return userDao.findByUserId(userId);
    }
}

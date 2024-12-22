package com.backend.service.impl;

import com.backend.dao.UserDao;
import com.backend.exception.CarNotFoundException;
import com.backend.exception.UserNotFoundException;
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
        User response = userDao.findByUserId(userId);
        if (response == null) {
            LoggerService.warn("[UserService | findByUserId] User was not found in the table");
            throw new UserNotFoundException("User not found");
        }
        return response;
    }

    @Override
    public void existsByUserId(String userId) {
        if (!userDao.existsByUserId(userId)) {
            LoggerService.error("[UserService | Exists By userId] User is not found in the table");
            throw new CarNotFoundException("User not found");
        }
        LoggerService.info("[UserService | Exists By userId] User exists in table");
    }
}

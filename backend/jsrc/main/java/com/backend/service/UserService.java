package com.backend.service;

import com.backend.models.table.User;

public interface UserService {

    User findByUserId(String userId);
}

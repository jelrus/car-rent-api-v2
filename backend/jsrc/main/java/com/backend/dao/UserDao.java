package com.backend.dao;

import com.backend.models.table.User;

public interface UserDao {

    User create(User user);

    User findByUserId(String userId);

    Boolean existsByUserId(String userId);

    Boolean inSupportAgentsList(String email);
}
package com.backend.dao;

import com.backend.models.table.User;

public interface UserDao {

    void create(User user);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByRoleAndUsername(String role, String username);
}
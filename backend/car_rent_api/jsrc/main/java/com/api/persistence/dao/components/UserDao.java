package com.api.persistence.dao.components;

import com.api.persistence.models.entity.User;
import com.api.persistence.models.entity.types.UserRole;

public interface UserDao {

    User create(User user);

    User findById(String id);

    Boolean isExistsById(String id);

    UserRole assignRole(String email);
}
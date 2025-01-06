package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.persistence.models.entity.types.UserRole;

public interface UserDao {

    User create(User user);

    User findById(String id);

    Boolean isExistsById(String id);

    UserRole assignRole(String email);
}
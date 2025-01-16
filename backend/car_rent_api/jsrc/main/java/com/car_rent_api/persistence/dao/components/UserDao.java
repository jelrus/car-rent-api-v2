package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.persistence.models.entity.types.UserRole;

import java.util.List;

public interface UserDao {

    User put(User user);

    User findById(String id);

    Boolean isExistsById(String id);

    UserRole assignRole(String email);

    List<User> findSupportAgents();
}
package com.backend.dao;

import com.backend.models.table.User;
import com.backend.models.table.types.UserRole;

/**
 * UserDao is the interface, provides contracts for interaction with DynamoDB tables related to User entity.
 */
public interface UserDao {

    /**
     * Contract for creating User entity in DynamoDB table.
     *
     * @param user {@code User} requested for creation User entity in DynamoDB table
     * @return {@code User} created User entity in DynamoDB table
     */
    User create(User user);

    /**
     * Contract for finding User by id in DynamoDB table.
     *
     * @param id {@code String} requested id in UUID v4 format
     * @return {@code User} found User entity in DynamoDB table
     */
    User findByUserId(String id);

    /**
     * Contract for checking if User is present by id in DynamoDB table.
     *
     * @param userId {@code String} requested id in UUID v4 format
     * @return {@code Boolean} result of existence check from DynamoDB table
     */
    Boolean existsByUserId(String userId);

    /**
     * Contract for checking if User role is present in DynamoDB table.
     *
     * @param email {@code String} requested email
     * @return {@code Boolean} result of existence check from DynamoDB table
     */
    Boolean inSupportAgentsList(String email);

    /**
     * Contract for resolving User role based on presence in DynamoDB table.
     *
     * @param email {@code String} requested email
     * @return {@code UserRole} resolved User role
     */
    UserRole getRole(String email);
}
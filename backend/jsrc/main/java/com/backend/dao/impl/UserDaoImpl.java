package com.backend.dao.impl;

import com.backend.dao.UserDao;
import com.backend.models.table.User;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

public class UserDaoImpl implements UserDao {

    private final DynamoDbTable<User> userTable;
    private final Gson gson;

    public UserDaoImpl(DynamoDbEnhancedClient dbEnhancedClient, Gson gson) {
        this.userTable = dbEnhancedClient.table(Envs.USERS_TABLE, TableSchema.fromClass(User.class));
        this.gson = gson;
    }

    @Override
    public void create(User user) {
        LoggerService.warn("[UserDao|Create] Entered method with User {}", gson.toJson(user));

        LoggerService.warn("[UserDao|Create] Attempt to create requested user");
        PutItemEnhancedRequest<User> userItem = PutItemEnhancedRequest.builder(User.class).item(user).build();
        userTable.putItem(userItem);
        LoggerService.info("[UserDao|Create] User has been successfully created");

        LoggerService.warn("[UserDao|Create] Exiting method");
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public Boolean existsByRoleAndUsername(String role, String username) {
        return false;
    }
}
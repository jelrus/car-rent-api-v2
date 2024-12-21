package com.backend.dao.impl;

import com.backend.dao.UserDao;
import com.backend.models.table.SupportAgent;
import com.backend.models.table.User;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final DynamoDbTable<User> usersTable;
    private final DynamoDbTable<SupportAgent> supportAgentsTable;
    private final Gson gson;

    public UserDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.usersTable = dbClient.table(Envs.USERS_TABLE, TableSchema.fromClass(User.class));
        this.supportAgentsTable = dbClient.table(Envs.SUPPORT_AGENTS_TABLE, TableSchema.fromClass(SupportAgent.class));
        this.gson = gson;
    }

    @Override
    public User create(User user) {
        LoggerService.info("[UserDao | Create] Creating user request from {}", gson.toJson(user));
        PutItemEnhancedRequest<User> userRequest = PutItemEnhancedRequest.builder(User.class).item(user).build();
        LoggerService.info("[UserDao | Create Created user request {}", gson.toJson(userRequest));

        LoggerService.info("[UserDao | Create] Creating user");
        usersTable.putItem(userRequest);
        LoggerService.info("[UserDao | Create] Created user {}", gson.toJson(user));

        return user;
    }

    @Override
    public User findByUserId(String id) {
        LoggerService.info("[UserDao | Find By Id] Finding table id...");
        User user = usersTable.getItem(i -> i.key(Key.builder().partitionValue(id).build()));
        LoggerService.info("[UserDao | Find By Id] Exiting table find by id method with result {}",
                gson.toJson(user));

        return user;
    }

    @Override
    public Boolean existsByUserId(String userId) {
        Key userKey = Key.builder().partitionValue(userId).build();

        LoggerService.info("[UserDao | Exists By userId] Checking existence of user with userId = {}", userId);
        Boolean userExists = Optional.ofNullable(usersTable.getItem(r -> r.key(userKey))).isPresent();
        LoggerService.info("[UserDao | Exists By userId] Result of existence check for user with id = {} is {}",
                userId, userExists);

        return userExists;
    }

    @Override
    public Boolean inSupportAgentsList(String email) {
        Key saKey = Key.builder().partitionValue(email).build();

        LoggerService.info("[UserDao | Is Support Agent] Checking existence of support agent with email = {}", email);
        Boolean saExists = Optional.ofNullable(supportAgentsTable.getItem(r -> r.key(saKey))).isPresent();
        LoggerService.info("[UserDao | Is Support Agent] Result of existence check for support agent with email = " +
                        "{} is {}", email, saExists);

        return saExists;
    }
}
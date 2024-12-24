package com.backend.dao.components.impl;

import com.backend.dao.components.UserDao;
import com.backend.models.table.SupportAgent;
import com.backend.models.table.User;
import com.backend.models.table.types.UserRole;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.Optional;

/**
 * UserDaoImpl is the implementation of UserDao interface, interacts directly with DynamoDB tables related to User
 * entity, serves as mediator between DynamoDB Client and service layers.
 */
public class UserDaoImpl implements UserDao {

    /**
     * Provides DynamoDbTable Users table.
     */
    private final DynamoDbTable<User> usersTable;

    /**
     * Provides DynamoDbTable SupportAgents table.
     */
    private final DynamoDbTable<SupportAgent> supportAgentsTable;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs UserDaoImpl object with injected DynamoDbEnhancedClient and Gson, initiates Users and SupportAgents
     * table schemas loading from entity classes correspondingly.
     *
     * @param dbClient {@code DynamoDbEnhancedClient} injected DynamoDb Client
     * @param gson {@code Gson} injected Gson
     */
    public UserDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.usersTable = dbClient.table(Envs.USERS_TABLE, TableSchema.fromClass(User.class));
        this.supportAgentsTable = dbClient.table(Envs.SUPPORT_AGENTS_TABLE, TableSchema.fromClass(SupportAgent.class));
        this.gson = gson;
    }

    /**
     * Creates User entity in DynamoDB Users table.
     *
     * @param user {@code User} requested for creation User entity in DynamoDB Users table
     * @return {@code User} created User entity in DynamoDB Users table
     */
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

    /**
     * Finds User by id in DynamoDB Users table.
     *
     * @param id {@code String} requested id in UUID v4 format
     * @return {@code User} found User entity in DynamoDB Users table
     */
    @Override
    public User findByUserId(String id) {
        LoggerService.info("[UserDao | Find By Id] Finding table id...");
        User user = usersTable.getItem(i -> i.key(Key.builder().partitionValue(id).build()));
        LoggerService.info("[UserDao | Find By Id] Exiting table find by id method with result {}",
                gson.toJson(user));

        return user;
    }

    /**
     * Checks if User is present by id in DynamoDB Users table.
     *
     * @param userId {@code String} requested id in UUID v4 format
     * @return {@code Boolean} result of existence check from DynamoDB Users table
     */
    @Override
    public Boolean existsByUserId(String userId) {
        Key userKey = Key.builder().partitionValue(userId).build();

        LoggerService.info("[UserDao | Exists By userId] Checking existence of user with userId = {}", userId);
        Boolean userExists = Optional.ofNullable(usersTable.getItem(r -> r.key(userKey))).isPresent();
        LoggerService.info("[UserDao | Exists By userId] Result of existence check for user with id = {} is {}",
                userId, userExists);

        return userExists;
    }

    /**
     * Checks if User role is present in DynamoDB SupportAgents table.
     *
     * @param email {@code String} requested email
     * @return {@code Boolean} result of existence check from DynamoDB SupportAgents table
     */
    @Override
    public Boolean inSupportAgentsList(String email) {
        Key saKey = Key.builder().partitionValue(email).build();

        LoggerService.info("[UserDao | Is Support Agent] Checking existence of support agent with email = {}", email);
        Boolean saExists = Optional.ofNullable(supportAgentsTable.getItem(r -> r.key(saKey))).isPresent();
        LoggerService.info("[UserDao | Is Support Agent] Result of existence check for support agent with email = " +
                        "{} is {}", email, saExists);

        return saExists;
    }

    /**
     * Resolves User role based on presence in DynamoDB SupportAgents table.
     *
     * @param email {@code String} requested email
     * @return {@code UserRole} resolved User role
     */
    @Override
    public UserRole getRole(String email) {
        LoggerService.info("[UserDao | Get Role] Attempting to get role for email = {}", email);
        return inSupportAgentsList(email) ? (UserRole.SUPPORT_AGENT) : UserRole.CLIENT;
    }
}
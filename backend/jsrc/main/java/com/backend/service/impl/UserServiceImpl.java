package com.backend.service.impl;

import com.backend.dto.UserSignUpRequest;
import com.backend.dto.UserSignUpResponse;
import com.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static final String CLIENT_ROLE = "Client";
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();
    private final String tableUsers = System.getenv("USERS_TABLE");
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserSignUpResponse createUser(UserSignUpRequest userRequest) {

        logger.info("createUser");

        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableUsers)
                .item(getItem(userRequest))
                .build();
        try {
            dynamoDbClient.putItem(putItemRequest);
            logger.info("table item saved to the db");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        UserSignUpResponse response = new UserSignUpResponse();
        response.setRole(CLIENT_ROLE);
        response.setUserId(putItemRequest.item().get("userId").s());
        response.setUserImageUrl(putItemRequest.item().get("userImageUrl").s());
        response.setUsername(putItemRequest.item().get("username").s());

        return response;
    }

    private Map<String, AttributeValue> getItem(UserSignUpRequest userRequest) {

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
        item.put("role", AttributeValue.builder().s(CLIENT_ROLE).build());
        item.put("username", AttributeValue.builder().s(generateUsername(userRequest)).build());
        item.put("firstName", AttributeValue.builder().s(userRequest.getFirstName()).build());
        item.put("lastName", AttributeValue.builder().s(userRequest.getLastName()).build());
        item.put("email", AttributeValue.builder().s(userRequest.getEmail()).build());
        item.put("password", AttributeValue.builder().s(userRequest.getPassword()).build());
        // todo
        // where should I get userImageUrl ?
        item.put("userImageUrl", AttributeValue.builder().s("").build());

        return item;
    }

    private String generateUsername(UserSignUpRequest userSignUpRequest) {

        String baseUsername = userSignUpRequest.getFirstName() + " " + userSignUpRequest.getLastName();

        // todo
        // get list of users from bd with userSignUpRequest.getFirstName() and userSignUpRequest.getLastName()
        // if this list is not empty than username = baseUsername + (listOfUsers.size() + 1);

        return baseUsername;
    }

}

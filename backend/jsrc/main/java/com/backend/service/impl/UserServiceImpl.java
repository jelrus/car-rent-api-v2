package com.backend.service.impl;

import com.backend.dto.UserSignUpRequest;
import com.backend.dto.UserSignUpResponse;
import com.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  A service to work with user table
 */
public class UserServiceImpl implements UserService {

    private final String tableUsers = System.getenv("USERS_TABLE");
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();
    private static final String CLIENT_ROLE = "Client";
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserSignUpResponse createUser(UserSignUpRequest userRequest) throws Exception{

        logger.info("createUser");

        // checking if user with provided email is absent in database
        checkIfUserAbsent(userRequest.getEmail());

        // creating request for putting data into dynamoDB table
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableUsers)
                .item(getItem(userRequest))
                .build();
        // putting data into dynamoDB table
        try {
            dynamoDbClient.putItem(putItemRequest);
            logger.info("table item saved to the db");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        // creating response
        UserSignUpResponse response = new UserSignUpResponse();
        response.setRole(CLIENT_ROLE);
        response.setUserId(putItemRequest.item().get("userId").s());
        response.setUserImageUrl(putItemRequest.item().get("userImageUrl").s());
        response.setUsername(putItemRequest.item().get("username").s());

        return response;
    }

    /**
     *  Checks if user with provided email is absent in database

     * @param email provided email
     * @throws Exception in case if user with provided email is present in database
     */
    private void checkIfUserAbsent(String email) throws Exception {

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableUsers)
                .indexName("email_index")
                .keyConditionExpression("email = :emailValue")
                .expressionAttributeValues(Map.of(
                        ":emailValue", AttributeValue.builder().s(email).build()))
                .build();

        QueryResponse queryResponse = dynamoDbClient.query(queryRequest);

        if (queryResponse.count() != 0) {
            throw new Exception("User with email: " + email + " is present");
        }
    }

    /**
     * Creates dynamoDB item
     * @param userRequest user data
     * @return map with attributes of dynamoDB item
     */
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

    /**
     * Generates username
     * @param userSignUpRequest user data
     * @return username
     */
    private String generateUsername(UserSignUpRequest userSignUpRequest) {

        // todo
        // should i generate unique username ?

        return userSignUpRequest.getFirstName() + " " + userSignUpRequest.getLastName();
    }

}

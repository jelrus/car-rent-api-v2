package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.dto.UserSignUpRequest;
import com.backend.dto.UserSignUpResponse;
import com.backend.handler.EndpointHandler;
import com.backend.service.CognitoService;
import com.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PostUsersHandler implements EndpointHandler {

    private final Gson gson;
    private final UserService userService;
    private final CognitoService cognitoService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(PostUsersHandler.class);

    public PostUsersHandler(UserService userService, CognitoService cognitoService, Gson gson) {
        this.userService = userService;
        this.cognitoService = cognitoService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        logger.info("PostUsersHandler ");

        try {
            Map<String, Object> requestBody = objectMapper.readValue(requestEvent.getBody(), Map.class);

            UserSignUpRequest requestedUser = new UserSignUpRequest();
            requestedUser.setFirstName((String) requestBody.get("firstName"));
            requestedUser.setLastName((String) requestBody.get("lastName"));
            requestedUser.setEmail((String) requestBody.get("email"));
            requestedUser.setPassword((String) requestBody.get("password"));

            logger.info("requestedUser: {}", requestedUser);

            UserSignUpResponse response = userService.createUser(requestedUser);
            response.setAccessToken(cognitoService.getAccessToken(requestedUser));

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(201)
                    .withBody(gson.toJson(response));

        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500);
        }
    }
}

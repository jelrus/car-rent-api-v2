package com.backend.handler.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;
import com.backend.handler.EndpointHandler;
import com.backend.service.CognitoService;
import com.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *  A handler of 'POST' method and 'v1/users' path
 */
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
        logger.info("PostUsersHandler");
        try {
            // extracting user data from request body
            UserSignUpRequest requestedUser = extractRequestedUser(requestEvent.getBody());

            // adding new user to the database
            UserSignUpResponse response =
                    userService.create(requestedUser);

            // adding new user to the cognito pool
            cognitoService.addUserToCognito(requestedUser.getEmail(), requestedUser.getPassword());

            // authenticating new user
            response.setAccessToken(cognitoService.getAccessToken(requestedUser.getEmail(), requestedUser.getPassword()));

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(201)
                    .withBody(gson.toJson(response));

        } catch (Exception exception){
            logger.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }

    /**
     * Extracts user data from request body
     * @param body request body
     * @return UserSignUpRequest with user data
     * @throws Exception in case of invalid user parameters in the body
     */
    private UserSignUpRequest extractRequestedUser(String body) throws Exception {

        logger.info("extractRequestedUser");

        try {
            Map<String, Object> requestBody = objectMapper.readValue(body, Map.class);

            UserSignUpRequest requestedUser = new UserSignUpRequest();
            requestedUser.setFirstName((String) requestBody.get("firstName"));
            requestedUser.setLastName((String) requestBody.get("lastName"));
            requestedUser.setEmail((String) requestBody.get("email"));
            requestedUser.setPassword((String) requestBody.get("password"));

            // todo
            // validation

            logger.info("User data extracted");

            return requestedUser;

        } catch (Exception exception) {
            logger.error("Invalid user parameters");
            throw new Exception("Invalid user parameters");
        }
    }
}

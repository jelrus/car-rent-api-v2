package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.dto.UserSignInRequest;
import com.backend.dto.UserSignInResponse;
import com.backend.handler.EndpointHandler;
import com.backend.service.CognitoService;
import com.backend.service.UserService;
import com.google.gson.Gson;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostUsersLoginHandler implements EndpointHandler {
    private static final Logger logger = LoggerFactory.getLogger(PostUsersLoginHandler.class);

    private final Gson gson;

    private final CognitoService cognitoService;

    private final UserService userService;

    public PostUsersLoginHandler(Gson gson, CognitoService cognitoService, UserService userService) {
        this.gson = gson;
        this.cognitoService = cognitoService;
        this.userService = userService;
    }


    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            String requestBody = requestEvent.getBody();
            logger.info("SignIn request body: {}", requestBody);

            UserSignInRequest userSignInRequest = UserSignInRequest.fromJson(requestBody);
            String email = userSignInRequest.getEmail();
            String password = userSignInRequest.getPassword();

            UserSignInResponse response = userService.signInUser(email, password);
            String accessToken = cognitoService.getAccessToken(userSignInRequest.getEmail(), userSignInRequest.getPassword());

            response.setAccessToken(accessToken);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(gson.toJson(response));

        } catch (JSONException e) {
            logger.error("Error on request body parsing: {}", e.getMessage());

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Incorrect request, json is not correct: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Bad request: {}", e.getMessage());

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Invalid username/password supplied");
        }
    }
}

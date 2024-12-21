package com.backend.handler.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;
import com.backend.service.AuthService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class PostUsersHandler implements EndpointHandler {

    private final Gson gson;
    private final AuthService authService;

    public PostUsersHandler(AuthService authService, Gson gson) {
        this.authService = authService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("Received request {}", requestEvent.getBody());
        try {
            UserSignUpRequest request = gson.fromJson(requestEvent.getBody(), UserSignUpRequest.class);
            UserSignUpResponse response = authService.userSignUp(request);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(gson.toJson(response));
        } catch (Exception exception){
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(exception.getMessage());
        }
    }
}

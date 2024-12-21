package com.backend.handler.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.UserSignInRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.service.AuthService;
import com.google.gson.Gson;

public class PostUsersLoginHandler implements EndpointHandler {

    private final Gson gson;
    private final AuthService authService;

    public PostUsersLoginHandler(Gson gson, AuthService authService) {
        this.gson = gson;
        this.authService = authService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
       try {
           UserSignInRequest request = gson.fromJson(requestEvent.getBody(), UserSignInRequest.class);
           UserSignInResponse response = authService.userSignIn(request);

           return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(gson.toJson(response));
       } catch (Exception e) {
           return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(e.getMessage());
       }
    }
}

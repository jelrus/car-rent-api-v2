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

/**
 * Handler for processing POST requests to create new users.
 * This class handles API requests to register new users using the AuthService.
 */
public class PostUsersHandler implements EndpointHandler {

    private final Gson gson;
    private final AuthService authService;

    public PostUsersHandler(AuthService authService, Gson gson) {
        this.authService = authService;
        this.gson = gson;
    }

    /**
     * Handles the incoming POST request to register a new user.
     *
     * @param requestEvent The incoming API Gateway request event containing the user registration details.
     * @param context The execution context of the lambda function.
     * @return A response event with the registration result or an error message.
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("[PostUsersHandler | handle] Handling POST request with path '/v1/users' {}",
                gson.toJson(requestEvent));
        try {
            // deserialize the JSON body into a UserSignUpRequest object
            UserSignUpRequest request = gson.fromJson(requestEvent.getBody(), UserSignUpRequest.class);
            UserSignUpResponse response = authService.userSignUp(request);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(gson.toJson(response));
        } catch (Exception e){
            LoggerService.error("Error occurred: " + e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(e.getMessage());
        }
    }
}

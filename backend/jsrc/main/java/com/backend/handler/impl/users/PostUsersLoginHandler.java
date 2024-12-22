package com.backend.handler.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.AuthException;
import com.backend.exception.UserNotFoundException;
import com.backend.exception.ValidationSchemaException;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.UserSignInRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.service.AuthService;
import com.backend.utils.properties.JsonValidationSchema;
import com.backend.utils.services.JsonValidationService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.Map;

/**
 * PostUsersLoginHandler is the implementation of EndpointHandler, serves as handler for POST:/users/login endpoint,
 * initiates User authentication in Cognito IDP and DynamoDB Clients.
 */
public class PostUsersLoginHandler implements EndpointHandler {

    /**
     * Provides AuthService for auth logic operations.
     */
    private final AuthService authService;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Provides JsonValidationService for JSON schema validation.
     */
    private final JsonValidationService jsonValidationService;

    /**
     * Constructs PostUsersLoginHandler object with injected AuthService, Gson and JsonValidationService.
     *
     * @param authService {@code AuthService} injected Auth Service
     * @param gson {@code Gson} injected Gson
     * @param jsonValidationService {@code JsonValidationService} injected JSON Validation Service
     */
    public PostUsersLoginHandler(AuthService authService, Gson gson, JsonValidationService jsonValidationService) {
        this.gson = gson;
        this.authService = authService;
        this.jsonValidationService = jsonValidationService;
    }

    /**
     * Handles sign up request for POST:/users/login endpoint.
     * Validates request from event body, forwards request and receives response from Auth Service, validates response,
     * if received request and generated response are correct and there is no exception raised during this operation,
     * generates response for POST:/users/login with HTTP status code 200. Otherwise, will return error message with
     * status code 400.
     *
     * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
     * @param context {@code Context} Lambda executable context
     * @return {@code APIGatewayProxyResponseEvent} response as the result of handling request for POST:/users endpoint
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
       try {
           LoggerService.info("[POST @ /users/login] Request received {}", gson.toJson(event.getBody()));
           UserSignInRequest request = gson.fromJson(event.getBody(), UserSignInRequest.class);

           LoggerService.warn("[POST @ /users/login] Validating received request...");
           jsonValidationService.validateModelByJsonSchema(
                   JsonValidationSchema.LOGIN_REQUEST.getSchema(), gson.toJson(request)
           );
           LoggerService.info("[POST @ /users/login] Received request {} was validated successfully!",
                   gson.toJson(request));

           LoggerService.warn("[POST @ /users/login] Processing response...");
           UserSignInResponse response = authService.userSignIn(request);
           LoggerService.info("[POST @ /users/login] Response received");

           LoggerService.warn("[POST @ /users/login] Validating response {}", gson.toJson(response));
           jsonValidationService.validateModelByJsonSchema(
                   JsonValidationSchema.LOGIN_RESPONSE.getSchema(), gson.toJson(response)
           );
           LoggerService.info("[POST @ /users/login] Response was validated successfully!");

           return new APIGatewayProxyResponseEvent()
                   .withStatusCode(200)
                   .withBody(gson.toJson(response));
       }  catch (AuthException | UserNotFoundException | ValidationSchemaException serviceException){
           LoggerService.error("[POST @ /users/login] Request or response was interrupted due to {}",
                   gson.toJson(serviceException.getMessage()));
           return new APIGatewayProxyResponseEvent()
                   .withStatusCode(400)
                   .withBody(gson.toJson(Map.of("message", serviceException.getMessage())));
       }
    }
}

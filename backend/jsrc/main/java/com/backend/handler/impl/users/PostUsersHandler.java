package com.backend.handler.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.AuthException;
import com.backend.exception.UserNotFoundException;
import com.backend.exception.ValidationSchemaException;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;
import com.backend.service.AuthService;
import com.backend.utils.properties.JsonValidationSchema;
import com.backend.utils.services.JsonValidationService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.Map;

/**
 * PostUsersHandler is the implementation of EndpointHandler, serves as handler for POST:/users endpoint,
 * initiates User creation in Cognito IDP and DynamoDB Clients.
 */
public class PostUsersHandler implements EndpointHandler {

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
     * Constructs PostUsersHandler object with injected AuthService, Gson and JsonValidationService.
     *
     * @param authService {@code AuthService} injected Auth Service
     * @param gson {@code Gson} injected Gson
     * @param jsonValidationService {@code JsonValidationService} injected JSON Validation Service
     */
    public PostUsersHandler(AuthService authService, Gson gson, JsonValidationService jsonValidationService) {
        this.authService = authService;
        this.gson = gson;
        this.jsonValidationService = jsonValidationService;
    }

    /**
     * Handles sign up request for POST:/users endpoint.
     * Validates request from event body, forwards request and receives response from Auth Service, validates response,
     * if received request and generated response are correct and there is no exception raised during this operation,
     * generates response for POST:/users with HTTP status code 201. Otherwise, will return error message with status
     * code 400.
     *
     * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
     * @param context {@code Context} Lambda executable context
     * @return {@code APIGatewayProxyResponseEvent} response as the result of handling request for POST:/users endpoint
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        try {
            LoggerService.info("[POST @ /users] Request received {}", gson.toJson(event));
            UserSignUpRequest request = gson.fromJson(event.getBody(), UserSignUpRequest.class);

            LoggerService.warn("[POST @ /users] Validating received request...");
            jsonValidationService.validateModelByJsonSchema(
                    JsonValidationSchema.SIGNUP_REQUEST.getSchema(), gson.toJson(request)
            );
            LoggerService.info("[POST @ /users] Received request was validated successfully!");

            LoggerService.warn("[POST @ /users] Processing response...");
            UserSignUpResponse response = authService.userSignUp(request);
            LoggerService.info("[POST @ /users] Response received");

            LoggerService.warn("[POST @ /users] Validating response {}", gson.toJson(response));
            jsonValidationService.validateModelByJsonSchema(
                    JsonValidationSchema.SIGNUP_RESPONSE.getSchema(), gson.toJson(response)
            );
            LoggerService.info("[POST @ /users] Response was validated successfully!");

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(201)
                    .withBody(gson.toJson(response));
        } catch (AuthException | UserNotFoundException | ValidationSchemaException serviceException) {
            LoggerService.error("[POST @ /users] Request or response was interrupted due to {}",
                    gson.toJson(serviceException.getMessage()));
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(gson.toJson(Map.of("message", serviceException.getMessage())));
        }
    }
}

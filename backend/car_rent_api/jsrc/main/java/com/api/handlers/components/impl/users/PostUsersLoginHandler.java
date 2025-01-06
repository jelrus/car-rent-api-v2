package com.api.handlers.components.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.config.JsonValidationSchemes;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.users.UserLoginRequest;
import com.api.persistence.models.dto.users.UserLoginResponse;
import com.api.service.components.AuthService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.SchemaValidator;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class PostUsersLoginHandler implements EndpointHandler {

    private final AuthService authService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;

    public PostUsersLoginHandler(AuthService authService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter) {
        this.authService = authService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[PostUsersLoginHandler] Entering 'POST @ /users/login' method");

        try {
            UserLoginRequest request = gsonPrinter.print().fromJson(event.getBody(), UserLoginRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostUsersLoginHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.LOGIN_REQUEST, jsonRequest);
            LogPrinter.info("[PostUsersLoginHandler] Request was validated");

            UserLoginResponse response = authService.login(request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostUsersLoginHandler] Exiting 'POST @ /users/login' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        }  catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostUsersLoginHandler] Exiting 'POST @ /users/login' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PostUsersLoginHandler] Exiting 'POST @ /users/login' method");
            TransactionContext.clear();
        }
    }
}
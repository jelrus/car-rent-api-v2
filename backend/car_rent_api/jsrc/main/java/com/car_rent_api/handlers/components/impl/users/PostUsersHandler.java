package com.car_rent_api.handlers.components.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.users.UserSignUpRequest;
import com.car_rent_api.persistence.models.dto.users.UserSignUpResponse;
import com.car_rent_api.service.components.AuthService;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.SchemaValidator;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class PostUsersHandler implements EndpointHandler {

    private final AuthService authService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;

    public PostUsersHandler(AuthService authService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter) {
        this.authService = authService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[PostUsersHandler] Entering 'POST @ /users' method");

        try {
            UserSignUpRequest request = gsonPrinter.print().fromJson(event.getBody(), UserSignUpRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostUsersHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.SIGNUP, jsonRequest);
            LogPrinter.info("[PostUsersHandler] Request was validated");

            UserSignUpResponse response = authService.signUp(request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostUsersHandler] Exiting 'POST @ /users' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostUsersHandler] Exiting 'POST @ /users' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PostUsersHandler] Exiting 'POST @ /users' method");
            TransactionContext.clear();
        }
    }
}
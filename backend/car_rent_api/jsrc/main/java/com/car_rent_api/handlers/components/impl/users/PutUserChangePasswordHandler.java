package com.car_rent_api.handlers.components.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.users.ChangeUserPasswordRequest;
import com.car_rent_api.persistence.models.dto.users.ChangeUserPasswordResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.AuthService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PutUserChangePasswordHandler implements EndpointHandler {

    private final AuthService authService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PutUserChangePasswordHandler(AuthService authService, SchemaValidator schemaValidator,
                                        GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer) {
        this.authService = authService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PutUserChangePasswordHandler] Entering 'PUT @ /users/{id}/change-password' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PutUserChangePasswordHandler] Access token acquired {}", accessToken);

            String id = event.getPathParameters().get("id");
            LogPrinter.warn("[PutUserChangePasswordHandler] Path param acquired {}", id);

            checkPutPermissions(accessToken, id);
            LogPrinter.info("[PutUserChangePasswordHandler] Permissions granted");

            ChangeUserPasswordRequest request =
                    gsonPrinter.print().fromJson(event.getBody(), ChangeUserPasswordRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PutUserChangePasswordHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.USER_PASSWORD_UPDATE, jsonRequest);
            LogPrinter.info("[PutUserChangePasswordHandler] Request was validated");

            ChangeUserPasswordResponse response = authService.changePassword(accessToken, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PutUserChangePasswordHandler] Exiting 'PUT @ /users/{id}/change-password' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PutUserChangePasswordHandler] Exiting 'PUT @ /users/{id}/change-password' with error {}",
                    jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[PutUserChangePasswordHandler] Exiting 'PUT @ /users/{id}/change-password' method");
            TransactionContext.clear();
        }
    }

    private void checkPutPermissions(String accessToken, String id) {
        authorizer.secure()
                .accessToken(accessToken).targetId(id).checkNullity().checkUsersExistence()
                .prohibitForRoles(UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForNotSelfTargetId(UserRole.CLIENT)
                .build();
    }
}
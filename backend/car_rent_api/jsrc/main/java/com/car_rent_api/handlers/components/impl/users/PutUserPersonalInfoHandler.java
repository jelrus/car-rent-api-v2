package com.car_rent_api.handlers.components.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoFormData;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.UserService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PutUserPersonalInfoHandler implements EndpointHandler {

    private final UserService userService;
    private final GsonPrinter gsonPrinter;
    private final SchemaValidator schemaValidator;
    private final EndpointHandlerAuthorizer authorizer;

    public PutUserPersonalInfoHandler(UserService userService, GsonPrinter gsonPrinter, SchemaValidator schemaValidator,
                                      EndpointHandlerAuthorizer authorizer) {
        this.userService = userService;
        this.gsonPrinter = gsonPrinter;
        this.schemaValidator = schemaValidator;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PutUserPersonalInfoHandler] Entering 'PUT @ /users/{id}/personal-info' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PutUserPersonalInfoHandler] Access token acquired {}", accessToken);

            String id = event.getPathParameters().get("id");
            LogPrinter.warn("[PutUserPersonalInfoHandler] Path param acquired {}", id);

            checkPutPermissions(accessToken, id);
            LogPrinter.info("[PutUserPersonalInfoHandler] Permissions granted");

            PersonalInfoFormData request = gsonPrinter.print().fromJson(event.getBody(), PersonalInfoFormData.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PutUserPersonalInfoHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.USER_PROFILE_UPDATE, jsonRequest);
            LogPrinter.info("[PutUserPersonalInfoHandler] Request was validated");

            PersonalInfoResponse response = userService.updateProfile(id, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PutUserPersonalInfoHandler] Exiting 'PUT @ /users/{id}/personal-info' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PutUserPersonalInfoHandler] Exiting 'PUT @ /users/{id}/personal-info' with error {}",
                    jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[PutUserPersonalInfoHandler] Exiting 'PUT @ /users/{id}/personal-info' method");
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
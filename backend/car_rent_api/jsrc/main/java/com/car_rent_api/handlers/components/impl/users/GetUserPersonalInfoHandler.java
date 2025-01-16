package com.car_rent_api.handlers.components.impl.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.UserService;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetUserPersonalInfoHandler implements EndpointHandler {

    private final UserService userService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public GetUserPersonalInfoHandler(UserService userService, GsonPrinter gsonPrinter,
                                      EndpointHandlerAuthorizer authorizer) {
        this.userService = userService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[GetUserPersonalInfoHandler] Entering 'GET @ /users/{id}/personal-info' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[GetUserPersonalInfoHandler] Access token acquired {}", accessToken);

            String id = event.getPathParameters().get("id");
            LogPrinter.warn("[GetUserPersonalInfoHandler] Path param acquired {}", gsonPrinter.print().toJson(id));

            LogPrinter.info("[GetUserPersonalInfoHandler] Request accepted. Checking permissions.");
            checkGetPermissions(accessToken, id);
            LogPrinter.info("[GetUserPersonalInfoHandler] Permissions granted");

            PersonalInfoResponse response = userService.getProfile(id);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetUserPersonalInfoHandler] Exiting 'GET @ /users/{id}/personal-info' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetUserPersonalInfoHandler] Exiting 'GET @ /users/{id}/personal-info' with error {}",
                    jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[GetUserPersonalInfoHandler] Exiting 'GET @ //users/{id}/personal-info' method");
            TransactionContext.clear();
        }
    }

    private void checkGetPermissions(String accessToken, String id) {
        authorizer.secure()
                .accessToken(accessToken).targetId(id).checkNullity().checkUsersExistence()
                .prohibitForRoles(UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForNotSelfTargetId(UserRole.CLIENT)
                .build();
    }
}
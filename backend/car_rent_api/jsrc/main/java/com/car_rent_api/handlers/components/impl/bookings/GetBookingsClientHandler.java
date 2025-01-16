package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookingsResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetBookingsClientHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public GetBookingsClientHandler(BookingService bookingService, GsonPrinter gsonPrinter,
                                    EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[GetBookingsClientHandler] Entering 'GET @ /bookings/{clientId}' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PostBookingsHandler] Access token acquired {}", accessToken);

            String clientId = event.getPathParameters().get("clientId");
            LogPrinter.warn("[GetBookingsClientHandler] Path param acquired {}", gsonPrinter.print().toJson(clientId));

            LogPrinter.info("[GetBookingsClientHandler] Request accepted. Checking permissions.");
            checkGetPermissions(accessToken, clientId);
            LogPrinter.info("[GetBookingsClientHandler] Permissions granted");

            BookingsResponse response = bookingService.findByClientId(clientId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' method");
            TransactionContext.clear();
        }
    }

    private void checkGetPermissions(String accessToken, String clientId) {
        authorizer.secure()
                .accessToken(accessToken).targetId(clientId).checkNullity().checkUsersExistence()
                .prohibitForRoles(UserRole.ADMIN)
                .prohibitForTargetRoles(UserRole.SUPPORT_AGENT, UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForTargetRoles(UserRole.CLIENT, UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForNotSelfTargetId(UserRole.CLIENT)
                .prohibitForSelfTargetId(UserRole.SUPPORT_AGENT)
                .build();
    }
}
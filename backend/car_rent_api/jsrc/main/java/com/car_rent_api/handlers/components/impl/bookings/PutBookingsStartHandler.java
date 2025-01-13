package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class PutBookingsStartHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PutBookingsStartHandler(BookingService bookingService, GsonPrinter gsonPrinter,
                                   EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PutBookingsStartedHandler] Entering 'POST @ /bookings/{clientId}/{bookingId}/start' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PutBookingsStartedHandler] Access token acquired {}", accessToken);

            String clientId = event.getPathParameters().get("clientId");
            LogPrinter.warn("[PutBookingsStartedHandler] Client ID acquired {}", clientId);

            String bookingId = event.getPathParameters().get("bookingId");
            LogPrinter.warn("[PutBookingsStartedHandler] Booking ID acquired {}", bookingId);

            checkPutPermissions(accessToken, clientId);
            LogPrinter.info("[PutBookingsStartedHandler] Permissions granted");

            BookCarResponse response = bookingService.onServiceStarted(accessToken, bookingId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PutBookingsStartedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/start' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PutBookingsStartedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/start' " +
                    "with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PutBookingsStartedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/start' " +
                    "method");
            TransactionContext.clear();
        }
    }

    private void checkPutPermissions(String accessToken, String clientId) {
        authorizer.secure()
                .accessToken(accessToken).targetId(clientId).checkNullity().checkUsersExistence()
                .prohibitForRoles(UserRole.ADMIN, UserRole.CLIENT)
                .prohibitForTargetRoles(UserRole.SUPPORT_AGENT, UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForSelfTargetId(UserRole.SUPPORT_AGENT)
                .build();
    }
}
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

public class PutBookingsCancelHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PutBookingsCancelHandler(BookingService bookingService,  GsonPrinter gsonPrinter,
                                    EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PutBookingsCancelHandler] Entering 'POST @ /bookings/{clientId}/{bookingId}/cancel' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PutBookingsCancelHandler] Access token acquired {}", accessToken);

            String clientId = event.getPathParameters().get("clientId");
            LogPrinter.warn("[PutBookingsCancelHandler] Client ID acquired {}", clientId);

            String bookingId = event.getPathParameters().get("bookingId");
            LogPrinter.warn("[PutBookingsCancelHandler] Booking ID acquired {}", bookingId);

            checkPutPermissions(accessToken, clientId);
            LogPrinter.info("[PutBookingsCancelHandler] Permissions granted");

            BookCarResponse response = bookingService.cancel(bookingId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PutBookingsCancelHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/cancel' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PutBookingsCancelHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/cancel' " +
                    "with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[PutBookingsCancelHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/cancel' " +
                    "method");
            TransactionContext.clear();
        }
    }

    private void checkPutPermissions(String accessToken, String clientId) {
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
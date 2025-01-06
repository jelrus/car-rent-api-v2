package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookingsResponse;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetBookingsClientHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;

    public GetBookingsClientHandler(BookingService bookingService, GsonPrinter gsonPrinter) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetBookingsClientHandler] Entering 'GET @ /bookings/{clientId}' method");
        String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
        LogPrinter.warn("[PostBookingsHandler] Access token acquired {}", accessToken);
        String clientId = event.getPathParameters().get("clientId");
        LogPrinter.warn("[GetBookingsClientHandler] Path param acquired {}", gsonPrinter.print().toJson(clientId));

        try {
            LogPrinter.info("[GetBookingsClientHandler] Request accepted");

            BookingsResponse response = bookingService.findAllByClientId(accessToken, clientId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' with error {}",
                    jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetBookingsClientHandler] Exiting 'GET @ /bookings/{clientId}' method");
            TransactionContext.clear();
        }
    }
}
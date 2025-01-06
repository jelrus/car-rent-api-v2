package com.api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.config.JsonValidationSchemes;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.booking.BookCarRequest;
import com.api.persistence.models.dto.booking.BookCarResponse;
import com.api.service.components.BookingService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.SchemaValidator;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class PostBookingsHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;

    public PostBookingsHandler(BookingService bookingService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter) {
        this.bookingService = bookingService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[PostBookingsHandler] Entering 'POST @ /bookings' method");
        String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
        LogPrinter.warn("[PostBookingsHandler] Access token acquired {}", accessToken);

        try {
            BookCarRequest request = gsonPrinter.print().fromJson(event.getBody(), BookCarRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostBookingsHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.BOOKING_REQUEST, jsonRequest);
            LogPrinter.info("[PostBookingsHandler] Request was validated");

            BookCarResponse response = bookingService.create(accessToken, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostBookingsHandler] Exiting 'POST @ /bookings' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostBookingsHandler] Exiting 'POST @ /bookings' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PostBookingsHandler] Exiting 'POST @ /bookings' method");
            TransactionContext.clear();
        }
    }
}
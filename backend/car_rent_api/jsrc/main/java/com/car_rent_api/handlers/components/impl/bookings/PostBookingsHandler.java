package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookCarRequest;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PostBookingsHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PostBookingsHandler(BookingService bookingService, SchemaValidator schemaValidator,
                               GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PostBookingsHandler] Entering 'POST @ /bookings' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PostBookingsHandler] Access token acquired {}", accessToken);

            BookCarRequest request = gsonPrinter.print().fromJson(event.getBody(), BookCarRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostBookingsHandler] Request was acquired {}", jsonRequest);

            checkPostPermissions(accessToken, request.getClientId());
            LogPrinter.info("[PostBookingsHandler] Permissions granted");

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.BOOKING_CREATE, jsonRequest);
            LogPrinter.info("[PostBookingsHandler] Request was validated");

            BookCarResponse response = bookingService.create(accessToken, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostBookingsHandler] Exiting 'POST @ /bookings' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostBookingsHandler] Exiting 'POST @ /bookings' with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[PostBookingsHandler] Exiting 'POST @ /bookings' method");
            TransactionContext.clear();
        }
    }

    private void checkPostPermissions(String accessToken, String clientId) {
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
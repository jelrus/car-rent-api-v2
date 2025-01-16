package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookCarEditRequest;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PutBookingsEditHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PutBookingsEditHandler(BookingService bookingService, SchemaValidator schemaValidator,
                                  GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[EditBookingsHandler] Entering 'PUT @ /bookings/{clientId}/{bookingId}/edit' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[EditBookingsHandler] Access token acquired {}", accessToken);

            String clientId = event.getPathParameters().get("clientId");
            LogPrinter.warn("[EditBookingsHandler] Client ID acquired {}", clientId);

            String bookingId = event.getPathParameters().get("bookingId");
            LogPrinter.warn("[EditBookingsHandler] Booking ID acquired {}", bookingId);

            checkPutPermissions(accessToken, clientId);
            LogPrinter.info("[EditBookingsHandler] Permissions granted");

            BookCarEditRequest request = gsonPrinter.print().fromJson(event.getBody(), BookCarEditRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[EditBookingsHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.BOOKING_EDIT, jsonRequest);
            LogPrinter.info("[EditBookingsHandler] Request was validated");

            BookCarResponse response = bookingService.edit(bookingId, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[EditBookingsHandler] Exiting 'PUT @ /bookings/{clientId}/{bookingId}/edit' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[EditBookingsHandler] Exiting 'PUT @ /bookings/{clientId}/{bookingId}/edit' " +
                    "with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[EditBookingsHandler] Exiting 'PUT @ /bookings/{clientId}/{bookingId}/edit' method");
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
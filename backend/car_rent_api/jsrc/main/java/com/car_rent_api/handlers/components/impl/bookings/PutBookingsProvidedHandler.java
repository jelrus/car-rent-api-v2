package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.dto.booking.BookCarServiceProvidedRequest;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PutBookingsProvidedHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PutBookingsProvidedHandler(BookingService bookingService, SchemaValidator schemaValidator,
                                      GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.authorizer = authorizer;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[PutBookingsProvidedHandler] Entering 'POST @ /bookings/{clientId}/{bookingId}/provide' " +
                "method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PutBookingsProvidedHandler] Access token acquired {}", accessToken);

            String clientId = event.getPathParameters().get("clientId");
            LogPrinter.warn("[PutBookingsProvidedHandler] Client ID acquired {}", clientId);

            String bookingId = event.getPathParameters().get("bookingId");
            LogPrinter.warn("[PutBookingsProvidedHandler] Booking ID acquired {}", bookingId);

            checkPutPermissions(accessToken, clientId);
            LogPrinter.info("[PutBookingsProvidedHandler] Permissions granted");

            BookCarServiceProvidedRequest request =
                    gsonPrinter.print().fromJson(event.getBody(), BookCarServiceProvidedRequest.class);
            String jsonRequest =  gsonPrinter.print().toJson(request);
            LogPrinter.info("[PutBookingsProvidedHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.BOOKING_SERVICE_PROVIDED,
                    jsonRequest);
            LogPrinter.info("[PutBookingsProvidedHandler] Request was validated");

            BookCarResponse response = bookingService.onServiceProvided(accessToken, bookingId, request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PutBookingsProvidedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/provide' " +
                    "with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PutBookingsProvidedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/provide' " +
                    "with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[PutBookingsProvidedHandler] Exiting 'POST @ /bookings/{clientId}/{bookingId}/provide' " +
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
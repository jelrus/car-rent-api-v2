package com.car_rent_api.handlers.components.impl.bookings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.booking.GetAgentsResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetBookingsSupportAgents implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public GetBookingsSupportAgents(BookingService bookingService, GsonPrinter gsonPrinter,
                                    EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[GetBookingsSupportAgents] Entering 'GET @ /users/agents' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[GetBookingsSupportAgents] Access token acquired {}", accessToken);

            Map<String, String> queryParams = event.getQueryStringParameters();
            queryParams = queryParams == null ? Map.of() : queryParams;
            LogPrinter.warn("[GetBookingsSupportAgents] Query params acquired {}",
                    gsonPrinter.print().toJson(queryParams));

            LogPrinter.info("[GetBookingsClientHandler] Request accepted. Checking permissions.");
            checkGetPermissions(accessToken);
            LogPrinter.info("[GetBookingsClientHandler] Permissions granted");

            GetAgentsResponse response = bookingService.findByAgentsFilter(queryParams);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetBookingsSupportAgents] Exiting 'GET @ /users/agents' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetBookingsClientHandler] Exiting 'GET @ /users/agents' with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[GetBookingsClientHandler] Exiting 'GET @ /users/agents' method");
            TransactionContext.clear();
        }
    }

    private void checkGetPermissions(String accessToken) {
        authorizer.secure()
                .accessToken(accessToken)
                .prohibitForRoles(UserRole.SUPPORT_AGENT, UserRole.CLIENT)
                .build();
    }
}
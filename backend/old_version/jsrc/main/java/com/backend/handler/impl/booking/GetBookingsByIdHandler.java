package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.response.BookingsResponse;
import com.backend.service.BookingService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

/**
 * Handler for retrieving bookings by client ID.
 * This class handles API requests to fetch booking details for a specific client.
 */
public class GetBookingsByIdHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final Gson gson;

    public GetBookingsByIdHandler(BookingService bookingService, Gson gson) {
        this.bookingService = bookingService;
        this.gson = gson;
    }

    /**
     * Handles the incoming API request to retrieve bookings by client ID.
     *
     * @param requestEvent The incoming API Gateway request event.
     * @param context The execution context of the lambda function.
     * @return A response event with the booking details or an error message.
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {

        try {
            BookingsResponse response = new BookingsResponse();
            // extract client ID from the request path parameters
            String clientId = requestEvent.getPathParameters().get("clientId");
            LoggerService.info("[GetBookingsByIdHandler | handle] Handling GET request with path '/v1/bookings/{}",
                    clientId);

            // put bookings of client with given clientId in response
            response.setContent(bookingService.getBookingsByClientId(clientId));
            LoggerService.info("[GetBookingsByIdHandler | handle] Successfully processed request for client ID: {}. Response = {}",
                    clientId, gson.toJson(response));

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));

        } catch  (Exception exception){

            LoggerService.error("[GetBookingsByIdHandler | handle] Error processing request: {}",
                    exception.getMessage(), exception);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}

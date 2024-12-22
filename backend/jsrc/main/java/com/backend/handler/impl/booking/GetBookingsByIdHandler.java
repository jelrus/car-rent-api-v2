package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.response.BookingsResponse;
import com.backend.service.BookingService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class GetBookingsByIdHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final Gson gson;

    public GetBookingsByIdHandler(BookingService bookingService, Gson gson) {
        this.bookingService = bookingService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        String clientId = requestEvent.getPathParameters().get("clientId");
        LoggerService.info("[GetBookingsByIdHandler | handle] Handling GET request with path '/v1/bookings/{}",
                clientId);

        BookingsResponse response = new BookingsResponse();
        response.setContent(bookingService.getBookingsByClientId(clientId));

        LoggerService.info("[GetBookingsByIdHandler | handle] Response = {}", gson.toJson(response));

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));
    }

}

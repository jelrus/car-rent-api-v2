package com.backend.handler.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.service.BookingService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class GetCarsBookedDayByCarIdHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final Gson gson;

    public GetCarsBookedDayByCarIdHandler(BookingService bookingService, Gson gson) {
        this.bookingService = bookingService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Handling GET request with path '/v1/cars/{carId}/booked-days' {}",
                gson.toJson(requestEvent));

        String carId = requestEvent.getPathParameters().get("carId");
        LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] carId = {}", carId);

        //List<String> response = bookingService.getCarBookedDates(carId);//

        Map<String, List<String>> response = Map.of("content", bookingService.getCarBookedDates(carId));

        LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Response = {}", gson.toJson(response));

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));
    }
}

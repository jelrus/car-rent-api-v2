package com.backend.handler.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class GetCarsBookedDayByCarIdHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final CarService carService;
    private final Gson gson;

    public GetCarsBookedDayByCarIdHandler(BookingService bookingService, CarService carService, Gson gson) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            String carId = requestEvent.getPathParameters().get("carId");
            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Handling GET request with path '/v1/cars/{}/booked-days' {}",
                    carId, gson.toJson(requestEvent));

            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] carId = {}", carId);

            // checking if car is in the table
            carService.existsByCarId(carId);

            // getting booked days of car with given carId
            Map<String, List<String>> response = Map.of("content", bookingService.getCarBookedDates(carId));
            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Response = {}", gson.toJson(response));

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));

        } catch  (Exception exception){
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}

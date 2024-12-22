package com.backend.handler.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.CarNotFoundException;
import com.backend.handler.EndpointHandler;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * Handler for retrieving the booked days of a specific car by its ID.
 * This class handles API requests to fetch the dates on which a car is booked.
 */
public class GetCarsBookedDayByCarIdHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final CarService carService;
    private final Gson gson;

    public GetCarsBookedDayByCarIdHandler(BookingService bookingService, CarService carService, Gson gson) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.gson = gson;
    }

    /**
     * Handles the incoming GET request to retrieve the booked days for a specific car.
     *
     * @param requestEvent The incoming API Gateway request event containing the car ID.
     * @param context The execution context of the lambda function.
     * @return A response event with the booked dates or an error message.
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            String carId = requestEvent.getPathParameters().get("carId");
            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Handling GET request with path '/v1/cars/{}/booked-days' {}",
                    carId, gson.toJson(requestEvent));

            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] carId = {}", carId);

            // Verify the existence of the car in the database before proceeding
            carService.existsByCarId(carId);

            // get booked days of car with given carId
            Map<String, List<String>> response = Map.of("content", bookingService.getCarBookedDates(carId));
            LoggerService.info("[GetCarsBookedDayByCarIdHandler | handle] Successfully processed request for car ID: {} with response: {}"
                    , carId, gson.toJson(response));
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));

        } catch (CarNotFoundException e) {
            LoggerService.error("Car not found: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(e.getMessage());
        } catch (Exception e) {
            LoggerService.error("Internal server error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Internal server error occurred.");
        }
    }
}

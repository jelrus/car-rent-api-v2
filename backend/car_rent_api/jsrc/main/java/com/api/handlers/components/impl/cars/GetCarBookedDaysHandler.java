package com.api.handlers.components.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.cars.CarBookedDatesResponse;
import com.api.service.components.CarService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetCarBookedDaysHandler implements EndpointHandler {

    private final CarService carService;
    private final GsonPrinter gsonPrinter;

    public GetCarBookedDaysHandler(CarService carService, GsonPrinter gsonPrinter) {
        this.carService = carService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetCarBookDaysHandler] Entering 'GET @ /cars/{carId}/booked-days' method");
        String carId = event.getPathParameters().get("carId");
        LogPrinter.warn("[GetCarBookDaysHandler] Path param acquired {}", gsonPrinter.print().toJson(carId));

        try {
            LogPrinter.info("[GetCarBookDaysHandler] Request accepted");

            CarBookedDatesResponse response = carService.getBookedDays(carId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetCarBookDaysHandler] Exiting 'GET @ /cars/{carId}/booked-days' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetCarBookDaysHandler] Exiting 'GET @ /cars/{carId}/booked-days' with error {}",
                    jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetCarBookDaysHandler] Exiting 'GET @ /cars/{carId}/booked-days' method");
            TransactionContext.clear();
        }
    }
}
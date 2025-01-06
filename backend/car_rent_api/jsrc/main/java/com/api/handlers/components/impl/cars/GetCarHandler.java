package com.api.handlers.components.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.cars.CarDetailsResponse;
import com.api.service.components.CarService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetCarHandler implements EndpointHandler {

    private final CarService carService;
    private final GsonPrinter gsonPrinter;

    public GetCarHandler(CarService carService, GsonPrinter gsonPrinter) {
        this.carService = carService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetCarHandler] Entering 'GET @ /cars/{carId}' method");
        String carId = event.getPathParameters().get("carId");
        LogPrinter.warn("[GetCarHandler] Path param acquired {}", gsonPrinter.print().toJson(carId));

        try {
            LogPrinter.info("[GetCarHandler] Request accepted");

            CarDetailsResponse response = carService.findById(carId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetCarHandler] Exiting 'GET @ /cars/{carId}' ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetCarHandler] Exiting 'GET @ /cars/{carId}' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetCarHandler] Exiting 'GET @ /cars/{carId}' method");
            TransactionContext.clear();
        }
    }
}
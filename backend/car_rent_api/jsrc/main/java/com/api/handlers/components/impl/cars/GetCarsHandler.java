package com.api.handlers.components.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.cars.FilterCarsPageableResponse;
import com.api.service.components.CarService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetCarsHandler implements EndpointHandler {

    private final CarService carService;
    private final GsonPrinter gsonPrinter;

    public GetCarsHandler(CarService carService, GsonPrinter gsonPrinter) {
        this.carService = carService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetCarsHandler] Entering 'GET @ /cars' method");
        Map<String, String> queryParams = event.getQueryStringParameters();
        LogPrinter.warn("[GetCarsHandler] Query params acquired {}", gsonPrinter.print().toJson(queryParams));

        try {
            LogPrinter.info("[GetCarsHandler] Request accepted");

            FilterCarsPageableResponse response = carService.findCarsFiltered(queryParams);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetCarsHandler] Exiting 'GET @ /cars' ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetCarsHandler] Exiting 'GET @ /cars' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetCarsHandler] Exiting 'GET @ /cars' method");
            TransactionContext.clear();
        }
    }
}
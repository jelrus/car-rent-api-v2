package com.api.handlers.components.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.location.LocationsResponse;
import com.api.service.components.LocationService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetHomeLocationsHandler implements EndpointHandler {

    private final LocationService locationService;
    private final GsonPrinter gsonPrinter;

    public GetHomeLocationsHandler(LocationService locationService, GsonPrinter gsonPrinter) {
        this.locationService = locationService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetHomeLocationsHandler] Entering 'GET @ /home/locations' method");

        try {
            LogPrinter.info("[GetHomeLocationsHandler] Request accepted");

            LocationsResponse response = locationService.findAll();
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetHomeLocationsHandler] Exiting 'GET @ /home/locations' with response ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetHomeLocationsHandler] Exiting 'GET @ /home/locations' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetHomeLocationsHandler] Exiting 'GET @ /home/locations' method");
            TransactionContext.clear();
        }
    }
}
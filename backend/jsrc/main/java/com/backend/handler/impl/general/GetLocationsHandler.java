package com.backend.handler.impl.general;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.ContentNotFoundException;
import com.backend.handler.EndpointHandler;
import com.backend.service.components.LocationService;
import com.google.gson.Gson;

import java.util.Map;

public class GetLocationsHandler implements EndpointHandler {

    private final LocationService locationsService;

    private final Gson gson;

    public GetLocationsHandler(LocationService locationsService, Gson gson) {
        this.locationsService = locationsService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(gson.toJson(locationsService.findAll()));
        } catch (ContentNotFoundException contentNotFoundException) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody(gson.toJson(Map.of("message", contentNotFoundException.getMessage())));
        }
    }
}
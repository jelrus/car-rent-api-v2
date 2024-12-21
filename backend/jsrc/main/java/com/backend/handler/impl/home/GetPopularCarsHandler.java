package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.response.PopularCarsResponse;
import com.backend.models.table.types.CarCategory;
import com.backend.service.PopularCarsService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetPopularCarsHandler implements EndpointHandler {

    private final Gson gson;
    private final PopularCarsService popularCarsService;
    private static final String DEFAULT_CATEGORY = CarCategory.BUSINESS.getCategory();

    public GetPopularCarsHandler(PopularCarsService popularCarsService, Gson gson) {
        this.gson = gson;
        this.popularCarsService = popularCarsService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("GetPopularCarsHandler");

        Map<String, String> queryStringParameters = Objects.nonNull(requestEvent.getQueryStringParameters())
                ? requestEvent.getQueryStringParameters()
                : new HashMap<>();

        try {
            PopularCarsResponse allByCategory = popularCarsService.findAllByCategory(
                    CarCategory.valueOf(
                            queryStringParameters.getOrDefault("category", DEFAULT_CATEGORY).toUpperCase()));

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(gson.toJson(allByCategory));

        } catch (Exception exception) {
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}

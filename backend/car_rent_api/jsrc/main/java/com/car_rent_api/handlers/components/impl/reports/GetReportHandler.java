package com.car_rent_api.handlers.components.impl.reports;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.persistence.models.report.ExportReportResponse;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetReportHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public GetReportHandler(BookingService bookingService, GsonPrinter gsonPrinter,
                            EndpointHandlerAuthorizer authorizer) {
        this.bookingService = bookingService;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        LogPrinter.warn("[GetReportHandler] Entering 'GET @ /reports/{extension}' method");
        TransactionContext.setTransactionId(UUID.randomUUID().toString());

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[GetReportHandler] Access token acquired {}", accessToken);

            String extension = event.getPathParameters().get("extension");
            LogPrinter.warn("[GetReportHandler] Path param acquired {}", gsonPrinter.print().toJson(extension));

            Map<String, String> queryParams = event.getQueryStringParameters();
            queryParams = queryParams == null ? Map.of() : queryParams;
            LogPrinter.warn("[GetReportHandler] Query params acquired {}",
                    gsonPrinter.print().toJson(queryParams));

            LogPrinter.info("[GetReportHandler] Request accepted. Checking permissions.");
            checkGetPermissions(accessToken);
            LogPrinter.info("[GetReportHandler] Permissions granted");

            ExportReportResponse response = bookingService.generateReport(extension, queryParams);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetReportHandler] Exiting 'GET @ /reports/{extension}' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonEx = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetReportHandler] Exiting 'GET @ /reports/{extension}' with error {}", jsonEx);
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonEx);
        } finally {
            LogPrinter.info("[GetReportHandler] Exiting 'GET @ /reports/{extension}' method");
            TransactionContext.clear();
        }
    }

    private void checkGetPermissions(String accessToken) {
        authorizer.secure()
                .accessToken(accessToken)
                .prohibitForRoles(UserRole.SUPPORT_AGENT, UserRole.CLIENT)
                .build();
    }
}
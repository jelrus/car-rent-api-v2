package com.car_rent_api.handlers.components.impl.feedbacks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.feedback.CreateFeedbackRequest;
import com.car_rent_api.persistence.models.dto.feedback.CreateFeedbacksResponse;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import com.car_rent_api.service.components.BookingService;
import com.car_rent_api.service.components.FeedbackService;
import com.car_rent_api.utils.components.*;

import java.util.Map;
import java.util.UUID;

public class PostFeedbacksHandler implements EndpointHandler {

    private final FeedbackService feedbackService;
    private final BookingService bookingService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;
    private final EndpointHandlerAuthorizer authorizer;

    public PostFeedbacksHandler(FeedbackService feedbackService, BookingService bookingService,
                                SchemaValidator schemaValidator, GsonPrinter gsonPrinter,
                                EndpointHandlerAuthorizer authorizer) {
        this.feedbackService = feedbackService;
        this.bookingService = bookingService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
        this.authorizer = authorizer;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[PostFeedbacksHandler] Entering 'POST @ /feedbacks' method");

        try {
            String accessToken = event.getHeaders().get("Authorization").replace("Bearer ", "");
            LogPrinter.warn("[PostFeedbacksHandler] Access token acquired {}", accessToken);

            CreateFeedbackRequest request = gsonPrinter.print().fromJson(event.getBody(), CreateFeedbackRequest.class);
            String jsonRequest = gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostFeedbacksHandler] Request was acquired {}", jsonRequest);

            checkPostPermissions(accessToken, request.getClientId());
            LogPrinter.info("[PostFeedbacksHandler] Permissions granted");

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.CREATE_FEEDBACK_REQUEST, jsonRequest);
            LogPrinter.info("[PostFeedbacksHandler] Request was validated");

            CreateFeedbacksResponse response = feedbackService.create(request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' with response ({})", jsonResponse);

            bookingService.onBookingFinished(request.getBookingId());
            LogPrinter.info("[PostFeedbacksHandler] Booking ID acquired");

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' method");
            TransactionContext.clear();
        }
    }

    private void checkPostPermissions(String accessToken, String clientId) {
        authorizer.secure()
                .accessToken(accessToken).targetId(clientId).checkNullity().checkUsersExistence()
                .prohibitForRoles(UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForTargetRoles(UserRole.CLIENT, UserRole.ADMIN, UserRole.SUPPORT_AGENT)
                .prohibitForNotSelfTargetId(UserRole.CLIENT)
                .build();
    }
}
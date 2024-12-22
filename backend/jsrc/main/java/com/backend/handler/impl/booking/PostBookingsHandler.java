package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookCarResponse;
import com.backend.service.AuthService;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.utils.services.LoggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class PostBookingsHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final AuthService authService;
    private final CarService carService;
    private final Gson gson;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PostBookingsHandler(BookingService bookingService, AuthService authService, CarService carService, Gson gson) {
        this.bookingService = bookingService;
        this.authService = authService;
        this.carService = carService;
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("[PostBookingsHandler | handle] Handling POST request with path '/v1/bookings' {}",
                gson.toJson(requestEvent));
        try {

            BookCarRequest request = gson.fromJson(requestEvent.getBody(), BookCarRequest.class);
            LoggerService.info("[PostBookingsHandler | handle] Booking request converted from requestEvent body {}",
                    gson.toJson(request));

            BookCarResponse response = new BookCarResponse();

            // 1. check user role
            // if user is not Client or Support Agent response 400 with "User must sign in first"


            // 2. check car available
            // if not response 400 with "Car is not available"

            // 3. make reservation in table Booking

            // message:
            //  "New booking was successfully created.
            //  Audi A6 Quattro 2023 is booked for Nov 11 - Nov 16
            //  You can change booking details until 10:30PM 10 Nov.
            //  Your order: #2437 (08.06.24)"

            // template:
            //  "New booking was successfully created.
            //  <carModel> is booked for <pickupDate> - <dropOffDate>
            //  You can change booking details until <pickupDateTime - 12H>.
            //  Your order: <bookingId> (<bookingDate>)"

            // fields in table Booking:
            // - bookingId
            // - bookingDateTime
            // - carId
            // - carModel
            // - carImageUrl
            // - clientId
            // - status
            // - dropOffDateTime;
            // - dropOffLocationId;
            // - pickupDateTime;
            // - pickupLocationId

            // fields in table Cars:
            // - carId
            // - carModel
            // - carImageUrl


            String message = bookingService.create(request);

            response.setMessage(message);
            LoggerService.info("[PostBookingsHandler | handle] Response message {}", message);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));

        } catch (Exception exception){
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }

//    private BookCarRequest extractBookCarRequest(String body) throws Exception {
//        LoggerService.info("[PostBookingsHandler | extractBookCarRequest] Extracting booking request from body {}", body);
//
//        try {
//            Map<String, Object> requestBody = objectMapper.readValue(body, Map.class);
//
//            BookCarRequest request = new BookCarRequest();
//            request.setCarId((String) requestBody.get("carId"));
//            request.setClientId((String) requestBody.get("clientId"));
//            request.setDropOffDateTime((String) requestBody.get("dropOffDateTime"));
//            request.setDropOffLocationId((String) requestBody.get("dropOffLocationId"));
//            request.setPickupDateTime((String) requestBody.get("pickupDateTime"));
//            request.setPickupLocationId((String) requestBody.get("pickupLocationId"));
//
//            LoggerService.info("[PostBookingsHandler | extractBookCarRequest] Booking request extracted {}", gson.toJson(request));
//
//
//            return request;
//
//        } catch (Exception exception) {
//            LoggerService.error("Invalid BookCarRequest parameters");
//            throw new Exception("Invalid BookCarRequest parameters");
//        }
//    }
}

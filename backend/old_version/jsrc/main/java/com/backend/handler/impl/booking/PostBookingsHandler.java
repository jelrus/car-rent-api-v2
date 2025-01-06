package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookCarResponse;
import com.backend.models.table.Booking;
import com.backend.models.table.CarEntity;
import com.backend.models.table.User;
import com.backend.models.table.types.BookingStatus;
import com.backend.models.table.types.UserRole;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.service.UserService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import static com.backend.utils.services.CustomDateTimeFormatter.formatter;

/**
 * Handler for processing POST requests to create bookings.
 * This class handles API requests to book a car for a specific user.
 */
public class PostBookingsHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final UserService userService;
    private final CarService carService;
    private final Gson gson;

    public PostBookingsHandler(BookingService bookingService, UserService userService, CarService carService, Gson gson) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.carService = carService;
        this.gson = gson;
    }

    /**
     * Handles the incoming POST request to create a new booking.
     *
     * @param requestEvent The incoming API Gateway request event containing the booking details.
     * @param context The execution context of the lambda function.
     * @return A response event with the result of the booking operation.
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("[PostBookingsHandler | handle] Handling POST request with path '/v1/bookings' {}",
                gson.toJson(requestEvent));
        BookCarResponse response = new BookCarResponse();

        try {
            // convert request body to BookCarRequest
            BookCarRequest request = gson.fromJson(requestEvent.getBody(), BookCarRequest.class);
            LoggerService.info("[PostBookingsHandler | handle] Booking request converted from requestEvent body {}",
                    gson.toJson(request));

            // set default booking status
            String status = BookingStatus.RESERVED.getStatus();

            // retrieve user from database
            User user = userService.findByUserId(request.getClientId());
            LoggerService.info("[PostBookingsHandler | handle] Found user {}", gson.toJson(user));

            // check if the user is a support agent and adjust the status accordingly
            if (user.getRole().equals(UserRole.SUPPORT_AGENT)){
                status = BookingStatus.RESERVED_BY_SUPPORT_AGENT.getStatus();
            }
            LoggerService.info("[PostBookingsHandler | handle] Status of booking {}", status);

            // fetching car
            CarEntity car = carService.findByCarId(request.getCarId());
            LoggerService.info("[PostBookingsHandler | handle] Car found {}", gson.toJson(car));

            // checking if car available
            carService.checkCarAvailable(request);

            String message = bookingService.create(convertToBooking(request, car, status));

            response.setMessage(message);
            LoggerService.info("[PostBookingsHandler | handle] Successfully processed booking for client ID: {}. response message is: {}",
                    request.getClientId(), message);
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(gson.toJson(response));

        } catch (Exception exception){
            LoggerService.error("[PostBookingsHandler | handle] Error processing booking request: {}",
                    exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }

    /**
     * Converts a booking request and car entity into a booking object.
     *
     * @param request The booking request containing user and car booking details.
     * @param car The car entity related to the booking.
     * @param status The status to be set for the new booking.
     * @return A fully populated booking object ready for persistence.
     */

    private Booking convertToBooking(BookCarRequest request, CarEntity car, String status) {
        LoggerService.info("[PostBookingsHandler | convertToBooking] Converting booking from request {}", gson.toJson(request));
        try {
            Booking booking = new Booking();
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setCarId(request.getCarId());
            booking.setClientId(request.getClientId());
            booking.setDropOffDateTime(request.getDropOffDateTime());
            booking.setDropOffLocationId(request.getDropOffLocationId());
            booking.setPickupDateTime(request.getPickupDateTime());
            booking.setPickupLocationId(request.getPickupLocationId());
            booking.setCarModel(car.getModel());
            booking.setBookingDateTime(LocalDateTime.now().format(formatter));
            booking.setStatus(status);
            booking.setCarImageUrl(car.getImageUrl());
            LoggerService.info("[PostBookingsHandler | convertToBooking] Converted booking {}", gson.toJson(booking));

            return booking;

        } catch (DateTimeParseException e) {
            LoggerService.error("Invalid date format");
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }
}

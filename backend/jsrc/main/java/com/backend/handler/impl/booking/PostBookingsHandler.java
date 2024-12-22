package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.CarNotAvailableException;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.backend.utils.services.CustomDateTimeFormatter.convertDateTimeToDate;
import static com.backend.utils.services.CustomDateTimeFormatter.formatter;

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

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("[PostBookingsHandler | handle] Handling POST request with path '/v1/bookings' {}",
                gson.toJson(requestEvent));
        BookCarResponse response = new BookCarResponse();

        try {
            // converting request body to BookCarRequest
            BookCarRequest request = gson.fromJson(requestEvent.getBody(), BookCarRequest.class);
            LoggerService.info("[PostBookingsHandler | handle] Booking request converted from requestEvent body {}",
                    gson.toJson(request));

            // getting user by userId and setting booking status
            String status = BookingStatus.RESERVED.getStatus();

            User user = userService.findByUserId(request.getClientId());
            LoggerService.info("[PostBookingsHandler | handle] Found user {}", gson.toJson(user));

            if (user.getRole().equals(UserRole.SUPPORT_AGENT)){
                status = BookingStatus.RESERVED_BY_SUPPORT_AGENT.getStatus();
            }
            LoggerService.info("[PostBookingsHandler | handle] Status of booking {}", status);

            // fetching car
            CarEntity car = carService.findByCarId(request.getCarId());
            LoggerService.info("[PostBookingsHandler | handle] Car found {}", gson.toJson(car));

            // checking if car available
            checkCarAvailable(request);

            String message = bookingService.create(convertToBooking(request, car, status));

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

    private Booking convertToBooking(BookCarRequest request, CarEntity car, String status) {
        LoggerService.info("[PostBookingsHandler | convertToBooking] Converting booking from request {}", gson.toJson(request));
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
    }

    private void checkCarAvailable(BookCarRequest request){
        List<String> bookedDates = bookingService.getCarBookedDates(request.getCarId());
        LocalDate endDate = LocalDate.parse(convertDateTimeToDate(request.getDropOffDateTime()));
        LocalDate startDate = LocalDate.parse(convertDateTimeToDate(request.getPickupDateTime()));
        boolean isAvailable = bookedDates.stream()
                .allMatch(d -> LocalDate.parse(d).isBefore(startDate) || LocalDate.parse(d).isAfter(endDate));
        LoggerService.info("[PostBookingsHandler | checkCarAvailable] Car available: {}", isAvailable);

        if (!isAvailable) {
            throw new CarNotAvailableException("The car is not available.");
        }
    }

}

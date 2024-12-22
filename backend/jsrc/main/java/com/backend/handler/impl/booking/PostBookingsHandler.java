package com.backend.handler.impl.booking;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookCarResponse;
import com.backend.models.table.Booking;
import com.backend.models.table.User;
import com.backend.models.table.types.BookingStatus;
import com.backend.models.table.types.UserRole;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.service.UserService;
import com.backend.utils.services.LoggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.backend.utils.services.CustomDateTimeFormatter.formatter;

public class PostBookingsHandler implements EndpointHandler {

    private final BookingService bookingService;
    private final UserService userService;
    private final CarService carService;
    private final Gson gson;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        try {

            BookCarRequest request = gson.fromJson(requestEvent.getBody(), BookCarRequest.class);
            LoggerService.info("[PostBookingsHandler | handle] Booking request converted from requestEvent body {}",
                    gson.toJson(request));

            BookCarResponse response = new BookCarResponse();
            String status = BookingStatus.RESERVED.getStatus();
            try {
                User user = userService.findByUserId(request.getClientId());
                LoggerService.info("[PostBookingsHandler | handle] Found user {}", gson.toJson(user));

                if (user.getRole().equals(UserRole.SUPPORT_AGENT)){
                    status = BookingStatus.RESERVED_BY_SUPPORT_AGENT.getStatus();
                }
                LoggerService.info("[PostBookingsHandler | handle] Status of booking {}", status);

            } catch (Exception e) {
                return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("User must sign in first");
            }

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


            String message = bookingService.create(convertToBooking(request, status));

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

    private Booking convertToBooking(BookCarRequest request, String status) {
        LoggerService.info("[BookingServiceImpl | convertToBooking] Converting booking from request {}", gson.toJson(request));
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCarId(request.getCarId());
        booking.setClientId(request.getClientId());
        booking.setDropOffDateTime(request.getDropOffDateTime());
        booking.setDropOffLocationId(request.getDropOffLocationId());
        booking.setPickupDateTime(request.getPickupDateTime());
        booking.setPickupLocationId(request.getPickupLocationId());
        booking.setCarModel("Audi");
        booking.setBookingDateTime(LocalDateTime.now().format(formatter));
        booking.setStatus(status);
        booking.setCarImageUrl("asdfasdf");

        LoggerService.info("[BookingServiceImpl | convertToBooking] Converted booking {}", gson.toJson(booking));
        return booking;
    }

}

package com.backend.mapper;

import com.backend.models.dto.request.booking.BookCarRequest;
import com.backend.models.dto.response.booking.BookingsResponse;
import com.backend.models.table.entities.actions.Booking;
import com.backend.models.table.entities.product.Car;
import com.backend.models.table.types.booking.BookingStatus;
import com.backend.models.table.types.client.ClientRole;

import java.time.LocalDateTime;

public class BookingMapper {
    public static BookingsResponse.BookingInfo mapBooking(Booking booking, Car car) {
        BookingsResponse.BookingInfo bookingInfo = new BookingsResponse.BookingInfo();
        /*bookingInfo.setBookingId(booking.getSkId());
        bookingInfo.setBookingStatus(booking.getStatus().getName());
        bookingInfo.setCarImageUrl(car.getImageUrl());
        bookingInfo.setCarModel(car.getModel());
        bookingInfo.setOrderDetails(booking.getOrderDetails());*/
        return bookingInfo;
    }

    public static Booking toBooking(BookCarRequest bookCarRequest, ClientRole clientRole, LocalDateTime date) {
        Booking booking = new Booking();
        booking.setCarId(bookCarRequest.getCarId());
        booking.setClientId(bookCarRequest.getClientId());
        booking.setDate(date.toString());

        if (clientRole == ClientRole.SUPPORT_AGENT) {
            booking.setStatus(BookingStatus.RESERVED);
        } else {
            booking.setStatus(BookingStatus.RESERVED_BY_SUPPORT_AGENT);
        }

        booking.setPickupLocationId(bookCarRequest.getPickupLocationId());
        booking.setDropOffLocationId(bookCarRequest.getDropOffLocationId());
        booking.setPickupDateTime(bookCarRequest.getPickupDateTime());
        booking.setDropOffDateTime(bookCarRequest.getDropOffDateTime());
        return booking;
    }
}
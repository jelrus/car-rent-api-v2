package com.backend.service;

import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookingInfo;
import com.backend.models.table.Booking;

import java.util.List;

public interface BookingService {

    String create(Booking booking);

    String update(BookCarRequest request);

    void delete(String bookingId);

    List<BookingInfo> getBookingsByClientId(String clientId);

    List<String> getCarBookedDates(String carId);

}

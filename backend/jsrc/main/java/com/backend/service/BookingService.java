package com.backend.service;

import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookingInfo;

import java.util.List;

public interface BookingService {

    String create(BookCarRequest request);

    String update(BookCarRequest request);

    void delete(String bookingId);

    List<BookingInfo> getBookingsByClientId(String clientId);

    List<String> getCarBookedDates(String carId);

}

package com.backend.dao;

import com.backend.models.table.Booking;

import java.util.List;

public interface BookingDao {

    Booking create(Booking booking);

    List<Booking> getCarBookedDates(String carId);

    List<Booking> getBookingsByClientId(String clientId);

}
package com.backend.service.impl;

import com.backend.dao.BookingDao;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookingInfo;
import com.backend.models.table.Booking;
import com.backend.service.BookingService;
import com.backend.service.UserService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.backend.utils.services.CustomDateTimeFormatter.convertDateTimeToDate;
import static com.backend.utils.services.CustomDateTimeFormatter.formatter;

/**
 * Service layer implementation for handling booking-related operations.
 * This includes creating, retrieving, and managing bookings.
 */
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final UserService userService;
    private final Gson gson;

    public BookingServiceImpl(BookingDao bookingDao, UserService userService, Gson gson) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.gson = gson;
    }

    /**
     * Creates a booking and returns a confirmation message.
     *
     * @param booking The booking details to be created.
     * @return A confirmation message detailing the booking.
     */
    @Override
    public String create(Booking booking) {

        LoggerService.info("[BookingServiceImpl | create] Creating booking from request {}", gson.toJson(booking));

        // creating booking
        Booking response = bookingDao.create(booking);

        String message = "New booking was successfully created. " +
                response.getCarModel() +
                " is booked for " +
                convertDateTimeToDate(response.getPickupDateTime()) +
                " - " +
                convertDateTimeToDate(response.getDropOffDateTime()) +
                ". You can change booking details until " +
                LocalDateTime.parse(response.getPickupDateTime(),formatter).plusHours(12).format(formatter) +
                " Your order: #" +
                response.getBookingId() +
                " (" +
                convertDateTimeToDate(response.getBookingDateTime()) +
                ")";
        LoggerService.info("[BookingServiceImpl | create] Booking created successfully. Message {}", message);

        return message;
    }

    @Override
    public String update(BookCarRequest request) {
        throw new UnsupportedOperationException("Update operation is not supported yet.");
    }

    @Override
    public void delete(String bookingId) {
        throw new UnsupportedOperationException("Delete operation is not supported yet.");
    }

    /**
     * Retrieves a list of booking information for a given client ID.
     * This method first checks if the user associated with the provided client ID exists.
     * If the user exists, it fetches all bookings made by this user.
     *
     * @param clientId The unique identifier of the client whose bookings are to be retrieved.
     * @return A list of BookingInfo objects containing details of each booking.
     */
    @Override
    public List<BookingInfo> getBookingsByClientId(String clientId) {
        LoggerService.info("[BookingServiceImpl | getBookingsByClientId] Getting booking by clientId = {}", clientId);

        // checking if user exists
        userService.existsByUserId(clientId);

        // getting bookings by given clientId
        List<BookingInfo> response = bookingDao.getBookingsByClientId(clientId).stream()
                .map(this::convertToBookingInfo)
                .toList();

        LoggerService.info("[BookingServiceImpl | getBookingsByClientId] Getting booking by clientId = {}", clientId);
        return response;
    }

    /**
     * Retrieves a list of all dates on which a specific car is booked.
     * This method fetches all bookings for the given car ID and calculates the range of dates
     * for each booking period.
     *
     * @param carId The unique identifier of the car for which booked dates are to be retrieved.
     * @return A sorted list of dates (as strings in ISO format) on which the car is booked.
     */
    @Override
    public List<String> getCarBookedDates(String carId) {
        List<String> response = new ArrayList<>();
        LoggerService.info("[BookingServiceImpl | getBookingsByCarId] Getting bookings by carId = {}", carId);

        // get bookings by given carId
        List<Booking> bookingList = bookingDao.getCarBookedDates(carId);

        // create list of booked dates
        bookingList.forEach(b -> {
            LocalDate startDate = LocalDateTime.parse(b.getPickupDateTime(), formatter).toLocalDate();
            LocalDate endDate = LocalDateTime.parse(b.getDropOffDateTime(), formatter).toLocalDate();

            List<String> dateList = Stream
                    .iterate(startDate, date -> !date.isAfter(endDate), date -> date.plusDays(1))
                    .map(LocalDate::toString)
                    .toList();
            response.addAll(dateList);
        });

        LoggerService.info("[BookingServiceImpl | getBookingsByCarId] Created list of booked dates of car carId = {} is {}",
                carId, gson.toJson(response));

        return response.stream().sorted().toList();
    }

    /**
     * Converts a Booking object into a BookingInfo object.
     *
     * @param booking The Booking object to be converted.
     * @return A BookingInfo object containing simplified and formatted booking details.
     */
    private BookingInfo convertToBookingInfo(Booking booking) {
        LoggerService.info("[BookingServiceImpl | convertToBookingInfo] Converting Booking to BookingInfo {}",
                gson.toJson(booking));

        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setBookingId(booking.getBookingId());
        bookingInfo.setBookingStatus(booking.getStatus());
        bookingInfo.setCarImageUrl(booking.getCarImageUrl());
        bookingInfo.setCarModel(booking.getCarModel());
        bookingInfo.setOrderDetails("#" + booking.getBookingId() + " (" + convertDateTimeToDate(booking.getBookingDateTime()) + ")");

        LoggerService.info("[BookingServiceImpl | convertToBooking] Successfully converted booking to BookingInfo: {}",
                gson.toJson(bookingInfo));
        return bookingInfo;
    }
}

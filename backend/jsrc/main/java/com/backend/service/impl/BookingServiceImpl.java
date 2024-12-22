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

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final UserService userService;
    private final Gson gson;

    public BookingServiceImpl(BookingDao bookingDao, UserService userService, Gson gson) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.gson = gson;
    }

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
        LoggerService.info("[BookingServiceImpl | create] Message {}", message);

        return message;
    }

    @Override
    public String update(BookCarRequest request) {
        return "";
    }

    @Override
    public void delete(String bookingId) {
    }

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

    @Override
    public List<String> getCarBookedDates(String carId) {
        List<String> response = new ArrayList<>();
        LoggerService.info("[BookingServiceImpl | getBookingsByCarId] Getting bookings by carId = {}", carId);

        // getting bookings by given carId
        List<Booking> bookingList = bookingDao.getCarBookedDates(carId);

        // creating list of booked dates
        bookingList.forEach(b -> {
            LocalDate startDate = LocalDateTime.parse(b.getPickupDateTime(), formatter).toLocalDate();
            LocalDate endDate = LocalDateTime.parse(b.getDropOffDateTime(), formatter).toLocalDate();

            List<String> dateList = Stream
                    .iterate(startDate, date -> !date.isAfter(endDate), date -> date.plusDays(1))
                    .map(LocalDate::toString)
                    .toList();

            LoggerService.info("[BookingServiceImpl | getBookingsByCarId] List of dates between {} and {} = {}",
                    startDate, endDate, gson.toJson(dateList));

            response.addAll(dateList);
        });
        LoggerService.info("[BookingServiceImpl | getBookingsByCarId] Created list of booked dates of car carId = {} is {}",
                carId, gson.toJson(response));

        return response.stream().sorted().toList();
    }

    private BookingInfo convertToBookingInfo(Booking booking) {
        LoggerService.info("[BookingServiceImpl | convertToBookingInfo] Converting Booking to BookingInfo {}",
                gson.toJson(booking));

        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setBookingId(booking.getBookingId());
        bookingInfo.setBookingStatus(booking.getStatus());
        bookingInfo.setCarImageUrl(booking.getCarImageUrl());
        bookingInfo.setCarModel(booking.getCarModel());
        bookingInfo.setOrderDetails("#" + booking.getBookingId() + " (" + convertDateTimeToDate(booking.getBookingDateTime()) + ")");

        LoggerService.info("[BookingServiceImpl | convertToBooking] Converted booking {}", gson.toJson(bookingInfo));
        return bookingInfo;
    }
}

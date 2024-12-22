package com.backend.service.impl;

import com.backend.dao.BookingDao;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.dto.response.BookingInfo;
import com.backend.models.table.Booking;
import com.backend.service.BookingService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.backend.utils.services.CustomDateTimeFormatter.formatter;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final Gson gson;

    public BookingServiceImpl(BookingDao bookingDao, Gson gson) {
        this.bookingDao = bookingDao;
        this.gson = gson;
    }

    @Override
    public String create(BookCarRequest request) {
        LoggerService.info("[BookingServiceImpl | create] Creating booking from request {}", gson.toJson(request));

        Booking booking = bookingDao.create(convertToBooking(request));

        return "New booking was successfully created./n" +
                booking.getCarModel() +
                " is booked for " +
                booking.getPickupDateTime() + // todo pickupDate
                " - " +
                booking.getDropOffDateTime() + // todo dropOffDate
                "/nYou can change booking details until " +
                booking.getPickupDateTime() + // todo pickupDateTime - 12H
                "/nYour order: " +
                booking.getBookingId() +
                " (" +
                booking.getBookingDateTime() +
                ")";
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
        LoggerService.info("getBookingsByClientId");
        List<BookingInfo> bookingInfoList = new ArrayList<>();
        BookingInfo info = new BookingInfo();
        info.setBookingId("clientId = " + clientId);
        info.setOrderDetails("OrderDetails");
        bookingInfoList.add(info);
        return bookingInfoList;
    }

    @Override
    public List<String> getCarBookedDates(String carId) {
        LoggerService.info("[BookingServiceImpl | getBookingsByCarId] Getting bookings by car id = {}", carId);
        List<Booking> bookingList = bookingDao.getCarBookedDates(carId);
        List<String> response = new ArrayList<>();

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

    private Booking convertToBooking(BookCarRequest request) {
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
        booking.setBookingDateTime(LocalDate.now().toString());
        booking.setStatus("RESERVED");
        booking.setCarImageUrl("asdfasdf");

        LoggerService.info("[BookingServiceImpl | convertToBooking] Converted booking {}", gson.toJson(booking));
        return booking;
    }
}

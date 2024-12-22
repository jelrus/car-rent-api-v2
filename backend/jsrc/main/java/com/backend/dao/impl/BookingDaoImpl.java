package com.backend.dao.impl;

import com.backend.dao.BookingDao;
import com.backend.models.table.Booking;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingDaoImpl implements BookingDao {

    private final DynamoDbTable<Booking> bookingTable;
    private final Gson gson;

    public BookingDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.bookingTable = dbClient.table(Envs.BOOKING_TABLE, TableSchema.fromClass(Booking.class));
        this.gson = gson;
    }

    @Override
    public Booking create(Booking booking) {
        LoggerService.info("[BookingDao | Create] Creating booking request from {}", gson.toJson(booking));
        PutItemEnhancedRequest<Booking> bookingRequest =
                PutItemEnhancedRequest.builder(Booking.class).item(booking).build();
        LoggerService.info("[BookingDao | Create Created booking request {}", gson.toJson(bookingRequest));

        LoggerService.info("[BookingDao | Create] Creating booking");
        bookingTable.putItem(bookingRequest);
        LoggerService.info("[BookingDao | Create] Created booking {}", gson.toJson(booking));
        return booking;
    }

    @Override
    public List<Booking> getCarBookedDates(String carId) {
        LoggerService.info("[BookingDao | getCarBookedDates] Getting booked dates of car with carId = {}", carId);

        Map<String, AttributeValue> expressionValues =
                Map.of(":carIdValue", AttributeValue.builder().s(carId).build());

        ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .consistentRead(true)
                .filterExpression(Expression.builder()
                        .expression("carId = :carIdValue")
                        .expressionValues(expressionValues)
                        .build())
                .build();
        LoggerService.info("[BookingDao | getCarBookedDates] Scan request created {}", gson.toJson(expressionValues));

        List<Booking> bookingList = new ArrayList<>();
        bookingTable.scan(request).items().forEach(bookingList::add);
        LoggerService.info("[BookingDao | getCarBookedDates] List of bookings: {}", gson.toJson(bookingList));

        return bookingList;
    }

    @Override
    public List<Booking> getBookingsByClientId(String clientId) {
        LoggerService.info("[BookingDao | getBookingsByClientId] Getting bookings by clientId = {}", clientId);

        Map<String, AttributeValue> expressionValues =
                Map.of(":clientIdValue", AttributeValue.builder().s(clientId).build());

        ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .consistentRead(true)
                .filterExpression(Expression.builder()
                        .expression("clientId = :clientIdValue")
                        .expressionValues(expressionValues)
                        .build())
                .build();
        LoggerService.info("[BookingDao | getBookingsByClientId] Scan request created {}", gson.toJson(expressionValues));

        List<Booking> bookingList = new ArrayList<>();
        bookingTable.scan(request).items().forEach(bookingList::add);
        LoggerService.info("[BookingDao | getBookingsByClientId] List of bookings: {}", gson.toJson(bookingList));

        return bookingList;
    }
}
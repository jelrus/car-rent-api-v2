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

/**
 * Data access object (DAO) implementation for managing booking operations in DynamoDB.
 * This class provides methods to create bookings and retrieve booking details by car ID and client ID.
 */
public class BookingDaoImpl implements BookingDao {

    private final DynamoDbTable<Booking> bookingTable;
    private final Gson gson;

    public BookingDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.bookingTable = dbClient.table(Envs.BOOKING_TABLE, TableSchema.fromClass(Booking.class));
        this.gson = gson;
    }

    /**
     * Creates a new booking in the DynamoDB table.
     *
     * @param booking The booking object to be stored.
     * @return The booking object after it has been stored in the database.
     */
    @Override
    public Booking create(Booking booking) {
        LoggerService.info("[BookingDao | Create] Creating booking request from {}", gson.toJson(booking));

        // build the DynamoDB put request for the booking
        PutItemEnhancedRequest<Booking> bookingRequest =
                PutItemEnhancedRequest.builder(Booking.class).item(booking).build();

        // store the booking item in the DynamoDB tab
        LoggerService.info("[BookingDao | Create] Creating booking in DynamoDB");
        bookingTable.putItem(bookingRequest);
        LoggerService.info("[BookingDao | Create] Created booking {}", gson.toJson(booking));

        return booking;
    }

    /**
     * Retrieves all bookings for a specific car by its ID.
     *
     * @param carId The ID of the car for which bookings are to be retrieved.
     * @return A list of Booking objects associated with the specified car.
     */
    @Override
    public List<Booking> getCarBookedDates(String carId) {
        LoggerService.info("[BookingDao | getCarBookedDates] Getting booked dates of car with carId = {}", carId);

        // prepare the expression values for the scan filter
        Map<String, AttributeValue> expressionValues =
                Map.of(":carIdValue", AttributeValue.builder().s(carId).build());

        // create the scan request with a filter for the car ID
        ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .consistentRead(true)
                .filterExpression(Expression.builder()
                        .expression("carId = :carIdValue")
                        .expressionValues(expressionValues)
                        .build())
                .build();
        LoggerService.info("[BookingDao | getCarBookedDates] Scan request created {}", gson.toJson(expressionValues));

        // execute the scan and collect resul
        List<Booking> bookingList = new ArrayList<>();
        bookingTable.scan(request).items().forEach(bookingList::add);
        LoggerService.info("[BookingDao | getCarBookedDates] Retrieved bookings: {}", gson.toJson(bookingList));

        return bookingList;
    }

    /**
     * Retrieves all bookings made by a specific client by their client ID.
     *
     * @param clientId The ID of the client for whom bookings are to be retrieved.
     * @return A list of Booking objects associated with the specified client.
     */
    @Override
    public List<Booking> getBookingsByClientId(String clientId) {
        LoggerService.info("[BookingDao | getBookingsByClientId] Getting bookings by clientId = {}", clientId);

        // prepare the expression values for the scan filter with the clientId
        Map<String, AttributeValue> expressionValues =
                Map.of(":clientIdValue", AttributeValue.builder().s(clientId).build());

        // build the scan request with a filter expression targeting the clientId
        ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .consistentRead(true)
                .filterExpression(Expression.builder()
                        .expression("clientId = :clientIdValue")
                        .expressionValues(expressionValues)
                        .build())
                .build();
        LoggerService.info("[BookingDao | getBookingsByClientId] Scan request created {}", gson.toJson(expressionValues));

        // execute the scan and collect all matching bookings into a list
        List<Booking> bookingList = new ArrayList<>();
        bookingTable.scan(request).items().forEach(bookingList::add);
        LoggerService.info("[BookingDao | getBookingsByClientId] List of bookings: {}", gson.toJson(bookingList));

        return bookingList;
    }
}
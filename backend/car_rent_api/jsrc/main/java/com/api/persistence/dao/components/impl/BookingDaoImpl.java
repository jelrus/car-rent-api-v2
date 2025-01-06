package com.api.persistence.dao.components.impl;

import com.api.config.Resources;
import com.api.config.TableKeys;
import com.api.persistence.dao.components.BookingDao;
import com.api.persistence.models.entity.Booking;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

public class BookingDaoImpl implements BookingDao {

    private final DynamoDbTable<Booking> bookingVolume;

    public BookingDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.bookingVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Booking.class));
    }

    @Override
    public Booking put(Booking booking) {
        PutItemEnhancedRequest<Booking> bookingRequest = PutItemEnhancedRequest.builder(Booking.class).item(booking)
                .build();
        bookingVolume.putItem(bookingRequest);
        return booking;
    }

    @Override
    public Booking findById(String id) {
        Key bookingKey = Key.builder()
                .partitionValue(TableKeys.BOOKING_PK)
                .sortValue(TableKeys.BOOKING_SK_PREFIX + id)
                .build();
        GetItemEnhancedRequest bookingRequest = GetItemEnhancedRequest.builder().key(bookingKey).build();
        return bookingVolume.getItem(bookingRequest);
    }

    @Override
    public Boolean isExistsById(String id) {
        Key bookingKey = Key.builder()
                .partitionValue(TableKeys.BOOKING_PK)
                .sortValue(TableKeys.BOOKING_SK_PREFIX + id)
                .build();
        GetItemEnhancedRequest bookingRequest = GetItemEnhancedRequest.builder().key(bookingKey).build();
        return bookingVolume.getItem(bookingRequest) != null;
    }

    @Override
    public Integer getTotalCount() {
        Key bookingKey = Key.builder().partitionValue(TableKeys.BOOKING_PK)
                .sortValue(TableKeys.BOOKING_SK_PREFIX)
                .build();
        QueryConditional queryConditional = QueryConditional.sortBeginsWith(bookingKey);
        return bookingVolume.query(queryConditional).items().stream().toList().size();
    }

    @Override
    public List<Booking> findAllByClientIdSortedByCreatedAt(String clientId) {
        Key bookingsKey = Key.builder().partitionValue(TableKeys.BOOKING_PK).build();
        QueryConditional bookingsCondition = QueryConditional.keyEqualTo(bookingsKey);

        Expression clientIdExpression = Expression.builder()
                .expression("#clientId = :clientId")
                .expressionNames(Map.of("#clientId", "BOOKING#CLIENT_ID"))
                .expressionValues(Map.of(":clientId", AttributeValue.builder().s(clientId).build()))
                .build();

        QueryEnhancedRequest bookingsRequest = QueryEnhancedRequest.builder()
                .queryConditional(bookingsCondition)
                .scanIndexForward(false)
                .filterExpression(clientIdExpression)
                .build();

        SdkIterable<Page<Booking>> bookingPages = bookingVolume.index(TableKeys.BOOKING_CREATED_AT_IDX)
                .query(bookingsRequest);

        return bookingPages.stream().map(Page::items).flatMap(List::stream).toList();
    }
}
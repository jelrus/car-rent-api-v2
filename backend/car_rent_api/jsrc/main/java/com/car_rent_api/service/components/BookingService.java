package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.booking.*;

public interface BookingService {

    BookCarResponse create(String accessToken, BookCarRequest bookCarRequest);

    BookCarResponse edit(String bookingId, BookCarEditRequest bookCarRequest);

    BookCarResponse cancel(String bookingId);

    BookCarResponse onServiceStarted(String accessToken, String bookingId);

    BookCarResponse onServiceProvided(String accessToken, String bookingId,
                                      BookCarServiceProvidedRequest serviceProvidedRequest);

    BookingsResponse findByClientId(String clientId);
}
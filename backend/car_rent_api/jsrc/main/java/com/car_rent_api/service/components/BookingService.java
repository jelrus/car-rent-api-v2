package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.booking.BookCarRequest;
import com.car_rent_api.persistence.models.dto.booking.BookCarResponse;
import com.car_rent_api.persistence.models.dto.booking.BookingsResponse;

public interface BookingService {

    BookCarResponse create(String accessToken, BookCarRequest bookCarRequest);

    BookingsResponse findAllByClientId(String accessToken, String clientId);
}
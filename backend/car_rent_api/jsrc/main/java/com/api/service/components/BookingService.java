package com.api.service.components;

import com.api.persistence.models.dto.booking.BookCarRequest;
import com.api.persistence.models.dto.booking.BookCarResponse;
import com.api.persistence.models.dto.booking.BookingsResponse;

public interface BookingService {

    BookCarResponse create(String accessToken, BookCarRequest bookCarRequest);

    BookingsResponse findAllByClientId(String accessToken, String clientId);
}
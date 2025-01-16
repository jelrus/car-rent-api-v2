package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.booking.*;
import com.car_rent_api.persistence.models.report.ExportReportResponse;

import java.util.Map;

public interface BookingService {

    BookCarResponse create(String accessToken, BookCarRequest bookCarRequest);

    BookCarResponse edit(String bookingId, BookCarEditRequest bookCarRequest);

    BookCarResponse cancel(String bookingId);

    BookCarResponse onServiceStarted(String accessToken, String bookingId);

    BookCarResponse onServiceProvided(String accessToken, String bookingId,
                                      BookCarServiceProvidedRequest serviceProvidedRequest);

    BookingsResponse findByClientId(String clientId);

    void onBookingFinished(String bookingId);

    GetAgentsResponse findByAgentsFilter(Map<String, String> params);

    ExportReportResponse generateReport(String extension, Map<String, String> params);
}
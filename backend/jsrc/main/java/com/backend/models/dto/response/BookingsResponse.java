package com.backend.models.dto.response;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Payload for get bookings request
 */
public class BookingsResponse {

    @Expose
    private List<BookingInfo> content;

    public BookingsResponse() {
        this.content = new ArrayList<>();
    }

    public List<BookingInfo> getContent() {
        return List.copyOf(this.content);
    }

    public void setContent(List<BookingInfo> content) {
        this.content = List.copyOf(content);
    }

}

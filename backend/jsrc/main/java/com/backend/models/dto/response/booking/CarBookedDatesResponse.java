package com.backend.models.dto.response.booking;

import java.util.List;

public class CarBookedDatesResponse {

    private List<String> content;

    public CarBookedDatesResponse() {}

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
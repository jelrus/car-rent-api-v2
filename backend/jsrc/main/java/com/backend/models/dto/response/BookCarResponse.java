package com.backend.models.dto.response;

import com.google.gson.annotations.Expose;

/**
 * Response object for booking a car
 */
public class BookCarResponse {

    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

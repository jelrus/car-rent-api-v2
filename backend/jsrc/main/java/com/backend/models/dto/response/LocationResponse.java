package com.backend.models.dto.response;

import com.backend.models.dto.request.LocationInfo;

import java.util.List;

public class LocationResponse {
    private List<LocationInfo> content;

    public LocationResponse(List<LocationInfo> content) {
        this.content = content;
    }

    public LocationResponse() {
    }

    public List<LocationInfo> getContent() {
        return content;
    }

    public void setContent(List<LocationInfo> content) {
        this.content = content;
    }
}

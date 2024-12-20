package com.backend.models.dto.response;

import com.backend.models.dto.request.LocationInfo;

public class LocationsResponseBody {
    private LocationInfo content;

    public LocationsResponseBody(LocationInfo content) {
        this.content = content;
    }

    public LocationsResponseBody() {
    }

    public LocationInfo getContent() {
        return content;
    }

    public void setContent(LocationInfo content) {
        this.content = content;
    }
}

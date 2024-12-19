package com.backend.dto.response;

import com.backend.dto.LocationInfo;

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

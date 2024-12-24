package com.backend.models.dto.response.car;

import java.util.List;

public class PopularCarsResponse {

    private List<CarBriefInfo> content;

    public PopularCarsResponse() {}

    public List<CarBriefInfo> getContent() {
        return content;
    }

    public void setContent(List<CarBriefInfo> content) {
        this.content = content;
    }
}
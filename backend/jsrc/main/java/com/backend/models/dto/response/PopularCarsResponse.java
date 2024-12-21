package com.backend.models.dto.response;

import com.backend.models.dto.request.CarBriefInfo;

import java.util.List;

public class PopularCarsResponse {
    private List<CarBriefInfo> content;

    public PopularCarsResponse(List<CarBriefInfo> content) {
        this.content = content;
    }

    public PopularCarsResponse() {
    }


    public List<CarBriefInfo> getContent() {
        return content;
    }

    public void setContent(List<CarBriefInfo> content) {
        this.content = content;
    }
}

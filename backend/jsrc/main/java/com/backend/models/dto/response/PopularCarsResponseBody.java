package com.backend.models.dto.response;

import com.backend.models.dto.request.CarBriefInfo;

import java.util.List;

public class PopularCarsResponseBody {
    private List<CarBriefInfo> content;

    public PopularCarsResponseBody(List<CarBriefInfo> content) {
        this.content = content;
    }

    public PopularCarsResponseBody() {
    }

    public List<CarBriefInfo> getContent() {
        return content;
    }

    public void setContent(List<CarBriefInfo> content) {
        this.content = content;
    }
}

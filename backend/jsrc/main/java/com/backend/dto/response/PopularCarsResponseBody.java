package com.backend.dto.response;

import com.backend.dto.CarBriefInfo;

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

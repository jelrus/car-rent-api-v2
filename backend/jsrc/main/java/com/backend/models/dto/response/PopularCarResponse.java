package com.backend.models.dto.response;

import com.backend.models.dto.request.CarBriefInfo;

import java.util.List;

public class PopularCarResponse {
    private List<CarBriefInfo> content;

    public PopularCarResponse(List<CarBriefInfo> content) {
        this.content = content;
    }

    public PopularCarResponse() {
    }


    public List<CarBriefInfo> getContent() {
        return content;
    }

    public void setContent(List<CarBriefInfo> content) {
        this.content = content;
    }
}

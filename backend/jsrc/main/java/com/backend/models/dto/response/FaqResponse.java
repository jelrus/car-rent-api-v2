package com.backend.models.dto.response;

import com.backend.models.dto.request.FaqStory;

import java.util.List;

public class FaqResponse {
    private List<FaqStory> content;

    public FaqResponse(List<FaqStory> content) {
        this.content = content;
    }

    public FaqResponse() {
    }

    public List<FaqStory> getContent() {
        return content;
    }

    public void setContent(List<FaqStory> content) {
        this.content = content;
    }
}

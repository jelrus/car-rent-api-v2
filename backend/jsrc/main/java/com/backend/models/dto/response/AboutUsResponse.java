package com.backend.models.dto.response;

import com.backend.models.dto.request.AboutUsStoryInfo;

import java.util.List;

public class AboutUsResponse {
    private List<AboutUsStoryInfo> content;

    public List<AboutUsStoryInfo> getContent() {
        return content;
    }

    public void setContent(List<AboutUsStoryInfo> content) {
        this.content = content;
    }

    public AboutUsResponse(List<AboutUsStoryInfo> content) {
        this.content = content;
    }
}

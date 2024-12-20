package com.backend.models.dto.response;

import com.backend.models.dto.request.AboutUsStoryInfo;

public class AboutUsResponseBody {
    private AboutUsStoryInfo content;

    public AboutUsResponseBody(AboutUsStoryInfo content) {
        this.content = content;
    }

    public AboutUsStoryInfo getContent() {
        return content;
    }

    public void setContent(AboutUsStoryInfo content) {
        this.content = content;
    }
}

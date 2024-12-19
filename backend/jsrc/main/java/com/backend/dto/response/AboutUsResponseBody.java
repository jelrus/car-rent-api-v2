package com.backend.dto.response;

import com.backend.dto.AboutUsStoryInfo;

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

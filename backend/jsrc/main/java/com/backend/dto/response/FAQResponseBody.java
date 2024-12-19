package com.backend.dto.response;

import com.backend.dto.FAQStory;

public class FAQResponseBody {
    private FAQStory content;

    public FAQResponseBody(FAQStory content) {
        this.content = content;
    }

    public FAQResponseBody() {
    }

    public FAQStory getContent() {
        return content;
    }

    public void setContent(FAQStory content) {
        this.content = content;
    }
}

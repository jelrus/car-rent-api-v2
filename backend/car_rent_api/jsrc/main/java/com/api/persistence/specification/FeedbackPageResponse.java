package com.api.persistence.specification;

import com.api.persistence.models.entity.Feedback;

import java.util.List;

public class FeedbackPageResponse {

    private List<Feedback> feedbacks;
    private int currentPage;
    private int totalPages;
    private int elementsOnPage;
    private int totalElements;

    public FeedbackPageResponse() {}

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public static Builder builder() {
        return new FeedbackPageResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder items(List<Feedback> feedbacks) {
            FeedbackPageResponse.this.feedbacks = feedbacks;
            return this;
        }

        public Builder currentPage(int currentPage) {
            FeedbackPageResponse.this.currentPage = currentPage;
            return this;
        }

        public Builder totalPages(int totalPages) {
            FeedbackPageResponse.this.totalPages = totalPages;
            return this;
        }

        public Builder elementsOnPage(int elementsOnPage) {
            FeedbackPageResponse.this.elementsOnPage = elementsOnPage;
            return this;
        }

        public Builder totalElements(int totalElements) {
            FeedbackPageResponse.this.totalElements = totalElements;
            return this;
        }

        public FeedbackPageResponse build() {
            return FeedbackPageResponse.this;
        }
    }
}
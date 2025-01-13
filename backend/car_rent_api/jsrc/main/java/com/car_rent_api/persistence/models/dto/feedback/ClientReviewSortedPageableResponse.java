package com.car_rent_api.persistence.models.dto.feedback;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ClientReviewSortedPageableResponse {

    @Expose
    private List<ClientReview> content;

    @Expose
    private Integer elementsOnPage;

    @Expose
    private Integer totalElements;

    @Expose
    private Integer currentPage;

    @Expose
    private Integer totalPages;

    public ClientReviewSortedPageableResponse() {}

    public List<ClientReview> getContent() {
        return content;
    }

    public Integer getElementsOnPage() {
        return elementsOnPage;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public static Builder builder() {
        return new ClientReviewSortedPageableResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<ClientReview> content) {
            ClientReviewSortedPageableResponse.this.content = content;
            return this;
        }

        public Builder elementsOnPage(Integer elementsOnPage) {
            ClientReviewSortedPageableResponse.this.elementsOnPage = elementsOnPage;
            return this;
        }

        public Builder totalElements(Integer totalElements) {
            ClientReviewSortedPageableResponse.this.totalElements = totalElements;
            return this;
        }

        public Builder currentPage(Integer currentPage) {
            ClientReviewSortedPageableResponse.this.currentPage = currentPage;
            return this;
        }

        public Builder totalPages(Integer totalPages) {
            ClientReviewSortedPageableResponse.this.totalPages = totalPages;
            return this;
        }

        public ClientReviewSortedPageableResponse build() {
            return ClientReviewSortedPageableResponse.this;
        }
    }
}
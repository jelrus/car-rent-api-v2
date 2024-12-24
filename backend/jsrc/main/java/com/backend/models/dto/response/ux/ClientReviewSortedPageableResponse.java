package com.backend.models.dto.response.ux;

import java.util.List;

public class ClientReviewSortedPageableResponse {

    private List<ClientReview> content;
    private Integer currentPage;
    private Integer totalElements;
    private Integer totalPages;

    public ClientReviewSortedPageableResponse() {}

    public List<ClientReview> getContent() {
        return content;
    }

    public void setContent(List<ClientReview> content) {
        this.content = content;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public static class ClientReview {
        private String author;
        private String authorImageUrl;
        private String date;
        private String rentalExperience;
        private String text;

        public ClientReview() {}

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorImageUrl() {
            return authorImageUrl;
        }

        public void setAuthorImageUrl(String authorImageUrl) {
            this.authorImageUrl = authorImageUrl;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRentalExperience() {
            return rentalExperience;
        }

        public void setRentalExperience(String rentalExperience) {
            this.rentalExperience = rentalExperience;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
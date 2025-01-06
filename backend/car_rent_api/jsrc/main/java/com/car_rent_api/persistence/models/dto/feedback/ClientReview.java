package com.car_rent_api.persistence.models.dto.feedback;

import com.google.gson.annotations.Expose;

public class ClientReview {

    @Expose
    private String author;

    @Expose
    private String authorImageUrl;

    @Expose
    private String date;

    @Expose
    private String rentalExperience;

    @Expose
    private String text;

    public ClientReview() {}

    public String getAuthor() {
        return author;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getRentalExperience() {
        return rentalExperience;
    }

    public String getText() {
        return text;
    }

    public static Builder builder() {
        return new ClientReview().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder author(String author) {
            ClientReview.this.author = author;
            return this;
        }

        public Builder authorImageUrl(String authorImageUrl) {
            ClientReview.this.authorImageUrl = authorImageUrl;
            return this;
        }

        public Builder date(String date) {
            ClientReview.this.date = date;
            return this;
        }

        public Builder rentalExperience(String rentalExperience) {
            ClientReview.this.rentalExperience = rentalExperience;
            return this;
        }

        public Builder text(String text) {
            ClientReview.this.text = text;
            return this;
        }

        public ClientReview build() {
            return ClientReview.this;
        }
    }
}
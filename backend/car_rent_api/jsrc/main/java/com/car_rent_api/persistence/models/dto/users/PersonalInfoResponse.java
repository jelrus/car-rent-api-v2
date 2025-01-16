package com.car_rent_api.persistence.models.dto.users;

import com.google.gson.annotations.Expose;

public class PersonalInfoResponse {

    @Expose
    private String clientId;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String imageUrl;

    @Expose
    private String postalCode;

    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private String street;

    @Expose
    private String phoneNumber;

    @Expose
    private String email;

    public PersonalInfoResponse() {}

    public String getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public static Builder builder() {
        return new PersonalInfoResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder clientId(String clientId) {
            PersonalInfoResponse.this.clientId = clientId;
            return this;
        }

        public Builder firstName(String firstName) {
            PersonalInfoResponse.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            PersonalInfoResponse.this.lastName = lastName;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            PersonalInfoResponse.this.imageUrl = imageUrl;
            return this;
        }

        public Builder postalCode(String postalCode) {
            PersonalInfoResponse.this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            PersonalInfoResponse.this.country = country;
            return this;
        }

        public Builder city(String city) {
            PersonalInfoResponse.this.city = city;
            return this;
        }

        public Builder street(String street) {
            PersonalInfoResponse.this.street = street;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            PersonalInfoResponse.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            PersonalInfoResponse.this.email = email;
            return this;
        }

        public PersonalInfoResponse build() {
            return PersonalInfoResponse.this;
        }
    }
}
package com.car_rent_api.persistence.models.dto.users;

import com.google.gson.annotations.Expose;

public class PersonalInfoFormData {

    @Expose
    private String avatar;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String phoneNumber;

    @Expose
    private String postalCode;

    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private String street;

    public PersonalInfoFormData() {}

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public static Builder builder() {
        return new PersonalInfoFormData().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder avatar(String avatar) {
            PersonalInfoFormData.this.avatar = avatar;
            return this;
        }

        public Builder firstName(String firstName) {
            PersonalInfoFormData.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            PersonalInfoFormData.this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            PersonalInfoFormData.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder postalCode(String postalCode) {
            PersonalInfoFormData.this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            PersonalInfoFormData.this.country = country;
            return this;
        }

        public Builder city(String city) {
            PersonalInfoFormData.this.city = city;
            return this;
        }

        public Builder street(String street) {
            PersonalInfoFormData.this.street = street;
            return this;
        }

        public PersonalInfoFormData build() {
            return PersonalInfoFormData.this;
        }
    }
}
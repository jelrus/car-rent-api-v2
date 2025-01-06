package com.api.persistence.models.dto.users;

import com.google.gson.annotations.Expose;

public class UserSignUpRequest {

    @Expose
    private String email;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String password;

    public UserSignUpRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
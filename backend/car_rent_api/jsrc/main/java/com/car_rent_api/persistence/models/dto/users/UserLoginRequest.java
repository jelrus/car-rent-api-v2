package com.car_rent_api.persistence.models.dto.users;

import com.google.gson.annotations.Expose;

public class UserLoginRequest {

    @Expose
    private String email;

    @Expose
    private String password;

    public UserLoginRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
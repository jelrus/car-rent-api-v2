package com.car_rent_api.persistence.models.dto.users;

import com.google.gson.annotations.Expose;

public class ChangeUserPasswordRequest {

    @Expose
    private String newPassword;

    @Expose
    private String oldPassword;

    public ChangeUserPasswordRequest() {}

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public static Builder builder() {
        return new ChangeUserPasswordRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder newPassword(String newPassword) {
            ChangeUserPasswordRequest.this.newPassword = newPassword;
            return this;
        }

        public Builder oldPassword(String oldPassword) {
            ChangeUserPasswordRequest.this.oldPassword = oldPassword;
            return this;
        }

        public ChangeUserPasswordRequest build() {
            return ChangeUserPasswordRequest.this;
        }
    }
}
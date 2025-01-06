package com.api.persistence.models.dto.users;

import com.api.config.TableKeys;
import com.api.persistence.models.entity.types.UserRole;
import com.google.gson.annotations.Expose;

public class UserSignUpResponse {

    @Expose
    private String accessToken;

    @Expose
    private String role;

    @Expose
    private String userId;

    @Expose
    private String userImageUrl;

    @Expose
    private String username;

    private UserSignUpResponse() {}

    public String getAccessToken() {
        return accessToken;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public static UserSignUpResponse.Builder builder() {
        return new UserSignUpResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder accessToken(String accessToken) {
            UserSignUpResponse.this.accessToken = accessToken;
            return this;
        }

        public Builder role(UserRole role) {
            UserSignUpResponse.this.role = role.getName();
            return this;
        }

        public Builder userId(String userId) {
            UserSignUpResponse.this.userId = userId.replace(TableKeys.USER_SK_PREFIX, "");
            return this;
        }

        public Builder userImageUrl(String userImageUrl) {
            UserSignUpResponse.this.userImageUrl = userImageUrl;
            return this;
        }

        public Builder username(String username) {
            UserSignUpResponse.this.username = username;
            return this;
        }

        public UserSignUpResponse build() {
            return UserSignUpResponse.this;
        }
    }
}
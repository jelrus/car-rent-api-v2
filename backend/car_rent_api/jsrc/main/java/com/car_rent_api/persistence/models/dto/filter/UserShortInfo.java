package com.car_rent_api.persistence.models.dto.filter;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class UserShortInfo {

    @Expose
    private String userId;

    @Expose
    private String username;

    public UserShortInfo() {}

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public static Builder builder() {
        return new UserShortInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder userId(String userId) {
            UserShortInfo.this.userId = userId.replace(TableKeys.USER_SK_PREFIX, "");
            return this;
        }

        public Builder username(String username) {
            UserShortInfo.this.username = username;
            return this;
        }

        public UserShortInfo build() {
            return UserShortInfo.this;
        }
    }
}
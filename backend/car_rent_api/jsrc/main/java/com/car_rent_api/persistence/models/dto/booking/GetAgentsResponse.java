package com.car_rent_api.persistence.models.dto.booking;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class GetAgentsResponse {

    @Expose
    private List<SupportAgentBookingInfo> content;

    @Expose
    private Map<String, Object> bookingsFilter;

    public GetAgentsResponse() {}

    public List<SupportAgentBookingInfo> getContent() {
        return content;
    }

    public Map<String, Object> getBookingsFilter() {
        return bookingsFilter;
    }

    public static Builder builder() {
        return new GetAgentsResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<SupportAgentBookingInfo> content) {
            GetAgentsResponse.this.content = content;
            return this;
        }

        public Builder bookingsFilter(Map<String, Object> bookingsFilter) {
            GetAgentsResponse.this.bookingsFilter = bookingsFilter;
            return this;
        }

        public GetAgentsResponse build() {
            return GetAgentsResponse.this;
        }
    }
}
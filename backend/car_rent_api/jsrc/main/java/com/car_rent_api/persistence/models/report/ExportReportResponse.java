package com.car_rent_api.persistence.models.report;

import com.google.gson.annotations.Expose;

public class ExportReportResponse {

    @Expose
    private String url;

    public ExportReportResponse() {}

    public String getUrl() {
        return url;
    }

    public static Builder builder() {
        return new ExportReportResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder url(String url) {
            ExportReportResponse.this.url = url;
            return this;
        }

        public ExportReportResponse build() {
            return ExportReportResponse.this;
        }
    }
}
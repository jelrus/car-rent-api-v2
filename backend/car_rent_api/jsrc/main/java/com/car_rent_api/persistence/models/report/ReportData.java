package com.car_rent_api.persistence.models.report;

import java.util.ArrayList;
import java.util.List;

public class ReportData {

    private List<String> headers;
    private List<Object> data;

    private ReportData() {}

    public List<String> getHeaders() {
        return headers;
    }

    public List<Object> getData() {
        return data;
    }

    public static Builder builder() {
        return new ReportData().new Builder();
    }

    public class Builder {

        private Builder() {
            headers = new ArrayList<>();
            data = new ArrayList<>();
        }

        public Builder headers(List<String> headers) {
            ReportData.this.headers = headers;
            return this;
        }

        public Builder data(List<Object> data) {
            ReportData.this.data = data;
            return this;
        }

        public ReportData build() {
            return ReportData.this;
        }
    }
}

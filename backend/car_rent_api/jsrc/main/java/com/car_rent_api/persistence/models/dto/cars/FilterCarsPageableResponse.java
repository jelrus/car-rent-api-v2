package com.car_rent_api.persistence.models.dto.cars;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class FilterCarsPageableResponse {

    @Expose
    private List<CarBriefInfo> content;

    @Expose
    private Integer elementsOnPage;

    @Expose
    private Integer totalElements;

    @Expose
    private Integer currentPage;

    @Expose
    private Integer totalPages;

    @Expose
    private Map<String, Object> components;

    public FilterCarsPageableResponse() {}

    public Integer getElementsOnPage() {
        return elementsOnPage;
    }

    public List<CarBriefInfo> getContent() {
        return content;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Map<String, Object> getComponents() {
        return components;
    }

    public static Builder builder() {
        return new FilterCarsPageableResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder content(List<CarBriefInfo> content) {
            FilterCarsPageableResponse.this.content = content;
            return this;
        }

        public Builder elementsOnPage(Integer elementsOnPage) {
            FilterCarsPageableResponse.this.elementsOnPage = elementsOnPage;
            return this;
        }

        public Builder totalElements(Integer totalElements) {
            FilterCarsPageableResponse.this.totalElements = totalElements;
            return this;
        }

        public Builder currentPage(Integer currentPage) {
            FilterCarsPageableResponse.this.currentPage = currentPage;
            return this;
        }

        public Builder totalPages(Integer totalPages) {
            FilterCarsPageableResponse.this.totalPages = totalPages;
            return this;
        }

        public Builder components(Map<String, Object> components) {
            FilterCarsPageableResponse.this.components = components;
            return this;
        }

        public FilterCarsPageableResponse build() {
            return FilterCarsPageableResponse.this;
        }
    }
}
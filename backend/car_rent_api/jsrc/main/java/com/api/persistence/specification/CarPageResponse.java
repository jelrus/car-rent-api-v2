package com.api.persistence.specification;

import com.api.persistence.models.entity.Car;

import java.util.List;

public class CarPageResponse {

    private List<Car> cars;
    private int currentPage;
    private int totalPages;
    private int elementsOnPage;
    private int totalElements;

    public CarPageResponse() {}

    public List<Car> getCars() {
        return cars;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public static Builder builder() {
        return new CarPageResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder items(List<Car> cars) {
            CarPageResponse.this.cars = cars;
            return this;
        }

        public Builder currentPage(int currentPage) {
            CarPageResponse.this.currentPage = currentPage;
            return this;
        }

        public Builder totalPages(int totalPages) {
            CarPageResponse.this.totalPages = totalPages;
            return this;
        }

        public Builder elementsOnPage(int elementsOnPage) {
            CarPageResponse.this.elementsOnPage = elementsOnPage;
            return this;
        }

        public Builder totalElements(int totalElements) {
            CarPageResponse.this.totalElements = totalElements;
            return this;
        }

        public CarPageResponse build() {
            return CarPageResponse.this;
        }
    }
}
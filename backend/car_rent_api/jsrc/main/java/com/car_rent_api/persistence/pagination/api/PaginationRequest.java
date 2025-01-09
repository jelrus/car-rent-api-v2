package com.car_rent_api.persistence.pagination.api;

import java.util.*;

public class PaginationRequest {

    private int page;
    private int size;
    private String sort;
    private boolean direction;
    private Map<String, String> indexes;

    public PaginationRequest() {}

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public boolean getDirection() {
        return direction;
    }

    public Map<String, String> getIndexes() {
        return indexes;
    }

    public static Builder builder() {
        return new PaginationRequest().new Builder();
    }

    public class Builder {

        private Builder() {
            PaginationRequest.this.indexes = new HashMap<>();
        }

        public Builder page(String page, int defaultPage) {
            PaginationRequest.this.page = page != null && isPositiveInteger(page)
                    ? Integer.parseInt(page)
                    : defaultPage;
            return this;
        }

        public Builder size(String size, int defaultSize) {
            PaginationRequest.this.size = size != null && isPositiveInteger(size)
                    ? Integer.parseInt(size)
                    : defaultSize;
            return this;
        }

        public Builder sort(String sort, String defaultSort) {
            PaginationRequest.this.sort = sort != null && indexes != null && !indexes.isEmpty()
                    ? indexes.get(sort.toLowerCase())
                    : defaultSort;
            return this;
        }

        public Builder direction(String direction, boolean defaultDirection) {
            if (direction == null){
                PaginationRequest.this.direction = defaultDirection;
            }

            if (direction != null && direction.equalsIgnoreCase("ASC")) {
                PaginationRequest.this.direction = true;
            }

            if (direction != null && direction.equalsIgnoreCase("DESC")) {
                PaginationRequest.this.direction = false;
            }

            return this;
        }

        public Builder indexes(Map<String, String> indexes) {
            PaginationRequest.this.indexes = indexes;
            return this;
        }

        public Builder defaultPage(int defaultPage) {
            PaginationRequest.this.page = defaultPage;
            return this;
        }

        public Builder defaultSize(int defaultSize) {
            PaginationRequest.this.size = defaultSize;
            return this;
        }

        public Builder defaultSort(String defaultSort) {
            PaginationRequest.this.sort = defaultSort;
            return this;
        }

        public Builder defaultDirection(boolean defaultDirection) {
            PaginationRequest.this.direction = defaultDirection;
            return this;
        }

        public PaginationRequest build() {
            return PaginationRequest.this;
        }

        private boolean isPositiveInteger(String value) {
            try {
                return Integer.parseInt(value) >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
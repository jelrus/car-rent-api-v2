package com.car_rent_api.persistence.pagination.api;

import software.amazon.awssdk.enhanced.dynamodb.Expression;

public class TableRequest {

    private PaginationRequest paginationRequest;
    private SpecificationRequest specificationRequest;
    private int page;
    private int size;
    private String sort;
    private boolean direction;
    private Expression filter;

    public TableRequest() {}

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

    public Expression getFilter() {
        return filter;
    }

    public static Builder builder() {
        return new TableRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pagination(PaginationRequest paginationRequest) {
            TableRequest.this.paginationRequest = paginationRequest;
            return this;
        }

        public Builder specification(SpecificationRequest specificationRequest) {
            TableRequest.this.specificationRequest = specificationRequest;
            return this;
        }

        public TableRequest build() {
            if (paginationRequest != null) {
                TableRequest.this.page = paginationRequest.getPage();
                TableRequest.this.size = paginationRequest.getSize();
                TableRequest.this.sort = paginationRequest.getSort();
                TableRequest.this.direction = paginationRequest.getDirection();
            }

            if (specificationRequest != null) {
                TableRequest.this.filter = specificationRequest.getFilter();
            }

            return TableRequest.this;
        }
    }
}
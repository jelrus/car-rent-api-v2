package com.car_rent_api.persistence.pagination.api;

import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.ArrayList;
import java.util.List;

public class TableResponse<E> {

    private List<E> items;
    private int page;
    private int totalPages;
    private int pageSize;
    private int elementsOnPage;
    private int totalElements;
    private TableRequest tableRequest;

    public TableResponse() {}

    public List<E> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public TableRequest getPageRequest() {
        return tableRequest;
    }

    public static <E> TableResponse<E>.Builder<E> builder() {
        return new TableResponse<E>().new Builder<E>();
    }

    public class Builder<T> {

        private Builder() {}

        public Builder<T> init(TableRequest tableRequest) {
            TableResponse.this.tableRequest = tableRequest;
            TableResponse.this.pageSize = tableRequest.getSize();
            TableResponse.this.page = tableRequest.getPage();
            TableResponse.this.items = new ArrayList<>();
            return this;
        }

        public Builder<T> convertFromPages(SdkIterable<Page<E>> pageable) {
            TableResponse.this.items = pageable.stream().map(Page::items).flatMap(List::stream).toList();
            return this;
        }

        public Builder<T> paginate() {
            TableResponse.this.totalElements = items.size();
            TableResponse.this.totalPages =
                    (int) Math.ceil((double) totalElements / pageSize);
            TableResponse.this.items = sliceItems(items);
            TableResponse.this.elementsOnPage = items.size();
            return this;
        }

        private List<E> sliceItems(List<E> items) {
            return page <= 0 || page > totalPages ? List.of() : page == totalPages
                    ? items.subList(pageSize * (page - 1), totalElements)
                    : items.subList(pageSize * (page - 1), pageSize * page);
        }

        public TableResponse<E> build() {
            return TableResponse.this;
        }
    }
}
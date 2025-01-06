package com.car_rent_api.persistence.specification;

import com.car_rent_api.utils.components.LogPrinter;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.ArrayList;
import java.util.List;

public class PaginationBuilder<E> {

    private List<E> items;
    private final int page;
    private int totalPages;
    private final int pageSize;
    private int elementsOnPage;
    private int totalElements;

    public PaginationBuilder(int destinationPage, int pageSize) {
        this.pageSize = pageSize;
        this.page = destinationPage;
        this.items = new ArrayList<>();
        this.totalPages = 0;
        this.elementsOnPage = 0;
        this.totalElements = 0;
    }

    public void paginate(SdkIterable<Page<E>> pageable) {
        LogPrinter.info("[PaginationBuilder] Entering pagination builder for cars filtering");
        List<E> allItems = pageable.stream().map(Page::items).flatMap(List::stream).toList();
        totalElements = allItems.size();
        totalPages = (int) Math.ceil((double) totalElements / pageSize);
        items = pageSlice(allItems);
        elementsOnPage = items.size();
        LogPrinter.info("Page {} from {} pages, {} items from {}", page, totalPages, elementsOnPage, totalElements);
    }

    private List<E> pageSlice(List<E> allItems) {
        return page <= 0 || page > totalPages ? List.of() : page == totalPages
                ? allItems.subList(pageSize * (page - 1), totalElements)
                : allItems.subList(pageSize * (page - 1), pageSize * page);
    }

    public List<E> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
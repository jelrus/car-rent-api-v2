package com.backend.models.dto.response.car;

import java.util.List;

public class FilterCarsPageableResponse {

    private List<CarBriefInfo> content;
    private Integer currentPage;
    private Long totalElements;
    private Integer totalPages;

    public FilterCarsPageableResponse() {}

    public List<CarBriefInfo> getContent() {
        return content;
    }

    public void setContent(List<CarBriefInfo> content) {
        this.content = content;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
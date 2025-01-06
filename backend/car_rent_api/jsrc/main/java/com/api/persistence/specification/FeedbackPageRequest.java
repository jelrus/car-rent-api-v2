package com.api.persistence.specification;

import com.api.config.TableKeys;
import com.api.exception.ExistenceException;
import com.api.utils.components.LogPrinter;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

public class FeedbackPageRequest {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 4;
    private static final String DEFAULT_SORT = TableKeys.FEEDBACK_DATE_IDX;
    private static final boolean DEFAULT_DIRECTION = false;

    private Map<String, String> params;
    private int page;
    private int size;
    private String sort;
    private boolean direction;
    private List<Expression> expressions;
    private Expression filter;

    public FeedbackPageRequest() {}

    public Map<String, String> getParams() {
        return params;
    }

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

    public List<Expression> getExpressions() {
        return expressions;
    }

    public Expression getFilter() {
        return filter;
    }

    public static Builder builder() {
        return new FeedbackPageRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder init(Map<String, String> params) {
            LogPrinter.info("[FeedbackPageRequest] Initializing FeedbackPageRequest with map {}", params);
            FeedbackPageRequest.this.params = Objects.requireNonNullElseGet(params, HashMap::new);
            FeedbackPageRequest.this.expressions = new ArrayList<>();
            return this;
        }

        public Builder toPage() {
            String page = params.get("page");

            if (page != null && isPositiveInteger(page)) {
                FeedbackPageRequest.this.page = Integer.parseInt(params.get("page"));
            } else {
                FeedbackPageRequest.this.page = DEFAULT_PAGE_NUMBER;
            }

            LogPrinter.info("[FeedbackPageRequest] Page {}", FeedbackPageRequest.this.page);
            return this;
        }

        public Builder forSize() {
            String size = params.get("size");

            if (size != null && isPositiveInteger(size)) {
                FeedbackPageRequest.this.size = Integer.parseInt(params.get("size"));
            } else {
                FeedbackPageRequest.this.size = DEFAULT_PAGE_SIZE;
            }

            LogPrinter.info("[FeedbackPageRequest] Size {}", FeedbackPageRequest.this.size);
            return this;
        }

        public Builder forSort() {
            String sort = params.get("sort");

            if (sort != null && (sort.equalsIgnoreCase("DATE") || sort.equalsIgnoreCase("RATING"))) {
                FeedbackPageRequest.this.sort = sort.equalsIgnoreCase("DATE")
                                ? TableKeys.FEEDBACK_DATE_IDX : TableKeys.FEEDBACK_RENTAL_EXPERIENCE_IDX;
            } else {
                FeedbackPageRequest.this.sort = DEFAULT_SORT;
            }

            LogPrinter.info("[FeedbackPageRequest] Sort {}", FeedbackPageRequest.this.sort);
            return this;
        }

        public Builder inDirection() {
            String direction = params.get("direction");

            if (direction != null && (direction.equalsIgnoreCase("ASC") || direction.equalsIgnoreCase("DESC"))) {
                FeedbackPageRequest.this.direction = direction.equalsIgnoreCase("ASC");
            } else {
                FeedbackPageRequest.this.direction = DEFAULT_DIRECTION;
            }

            LogPrinter.info("[FeedbackPageRequest] Direction {}", FeedbackPageRequest.this.direction);
            return this;
        }

        public Builder forCarId(String carId) {
            if (carId != null) {
                Expression carIdExpression = Expression.builder()
                        .expression("#carId = :carId")
                        .expressionNames(Map.of("#carId", "FEEDBACK#CAR_ID"))
                        .expressionValues(Map.of(":carId", AttributeValue.builder().s(carId).build()))
                        .build();
                expressions.add(carIdExpression);
            } else {
                LogPrinter.error("[FeedbackPageRequest] carId is {}", carId);
                throw new ExistenceException("Target car cannot be found");
            }

            LogPrinter.info("[FeedbackPageRequest] carId in expression {}",
                    FeedbackPageRequest.this.expressions.size());
            return this;
        }

        public FeedbackPageRequest build() {
            LogPrinter.info("[FeedbackPageRequest] Final expression size {}",
                    FeedbackPageRequest.this.expressions.size());
            FeedbackPageRequest.this.filter = Expression.join("AND", FeedbackPageRequest.this.expressions);
            return FeedbackPageRequest.this;
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
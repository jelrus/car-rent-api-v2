package com.api.persistence.specification;

import com.api.utils.components.LogPrinter;
import com.api.utils.components.StringDateConverter;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CarPageRequest {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 16;
    private static final int DEFAULT_MIN_PRICE = 0;
    private static final int DEFAULT_MAX_PRICE = Integer.MAX_VALUE;

    private Map<String, String> params;
    private List<Expression> expressions;
    private Expression filter;
    private int page;
    private int size;

    public CarPageRequest() {}

    public Map<String, String> getParams() {
        return params;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public Expression getFilter() {
        return filter;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public static Builder builder() {
        return new CarPageRequest().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder init(Map<String, String> params) {
            LogPrinter.info("[CarPageRequest] Initializing CarPageRequest with map {}", params);
            CarPageRequest.this.params = Objects.requireNonNullElseGet(params, HashMap::new);
            CarPageRequest.this.expressions = new ArrayList<>();
            return this;
        }

        public Builder toPage() {
            String page = params.get("page");

            if (page != null && isPositiveInteger(page)) {
                CarPageRequest.this.page = Integer.parseInt(params.get("page"));
            } else {
                CarPageRequest.this.page = DEFAULT_PAGE_NUMBER;
            }

            LogPrinter.info("[CarPageRequest] Page {}", CarPageRequest.this.page);
            return this;
        }

        public Builder forSize() {
            String size = params.get("size");

            if (size != null && isPositiveInteger(size)) {
                CarPageRequest.this.size = Integer.parseInt(params.get("size"));
            } else {
                CarPageRequest.this.size = DEFAULT_PAGE_SIZE;
            }

            LogPrinter.info("[CarPageRequest] Size {}", CarPageRequest.this.size);
            return this;
        }

        public Builder pickupLocationIdEquals() {
            String pickupLocationId = params.get("pickupLocationId");

            if (pickupLocationId != null) {
                Expression pickupLocationIdExpression = Expression.builder()
                        .expression("#pickupLocationId = :pickupLocationId")
                        .expressionNames(Map.of("#pickupLocationId", "CAR#PICKUP_LOCATION_ID"))
                        .expressionValues(Map.of(":pickupLocationId", AttributeValue.builder().s(pickupLocationId)
                                .build()))
                        .build();
                expressions.add(pickupLocationIdExpression);
            }

            LogPrinter.info("[CarPageRequest] pickupLocationId in expression {}",
                    CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder dropOffLocationIdInRangeDropOffLocationsIds() {
            String dropOffLocationId = params.get("dropOffLocationId");

            if (dropOffLocationId != null) {
                Expression pickupLocationIdExpression = Expression.builder()
                        .expression("contains(#dropOffLocationIds, :dropOffLocationId)")
                        .expressionNames(Map.of("#dropOffLocationIds", "CAR#DROPOFF_LOCATIONS_IDS"))
                        .expressionValues(Map.of(":dropOffLocationId", AttributeValue.builder().s(dropOffLocationId)
                                .build()))
                        .build();
                expressions.add(pickupLocationIdExpression);
            }

            LogPrinter.info("[CarPageRequest] dropOffLocationId in expression {}",
                    CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder pickupAndDropOffDatesRangeNotOverlappingBookedDays() {
            String pickupDateTime = params.get("pickupDateTime");
            LogPrinter.info("pickupDateTime before conversion {}", pickupDateTime);
            String dropOffDateTime = params.get("dropOffDateTime");
            LogPrinter.info("dropOffDateTime before conversion {}", dropOffDateTime);

            if (pickupDateTime != null && dropOffDateTime != null &&
                    StringDateConverter.isISO8601DateTime(pickupDateTime) &&
                    StringDateConverter.isISO8601DateTime(dropOffDateTime) &&
                    StringDateConverter.isISO8601DateTimeStartBeforeEnd(pickupDateTime, dropOffDateTime)) {
                List<String> pickupDropOffRange =
                        StringDateConverter.generateGermanDatesRange(pickupDateTime, dropOffDateTime);
                LogPrinter.info("[CarPageRequest] Pickup date range size {}, dates {}",
                        pickupDropOffRange.size(), pickupDropOffRange.toString());

                AtomicInteger memberCount = new AtomicInteger();

                pickupDropOffRange.forEach(s -> {
                    Expression pickupDateTimeExpression = Expression.builder()
                            .expression("NOT contains(#bookedDays, :rangedDateTime" + memberCount +")")
                            .expressionNames(Map.of("#bookedDays", "CAR#BOOKED_DAYS"))
                            .expressionValues(Map.of(":rangedDateTime" + memberCount,
                                    AttributeValue.builder().s(s).build())).build();
                    expressions.add(pickupDateTimeExpression);
                    memberCount.getAndIncrement();
                });
            }

            LogPrinter.info("[CarPageRequest] pickupDateTime in expression {}", CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder categoryEquals() {
            String category = params.get("category");

            if (category != null) {
                String enumCategory = category.toUpperCase().replace(" ", "_");
                Expression categoryExpression = Expression.builder()
                        .expression("#category = :category")
                        .expressionNames(Map.of("#category", "CAR#CATEGORY"))
                        .expressionValues(Map.of(":category", AttributeValue.builder().s(enumCategory).build()))
                        .build();
                expressions.add(categoryExpression);
            }

            LogPrinter.info("[CarPageRequest] category in expression {}", CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder gearBoxTypeEquals() {
            String gearBoxType = params.get("gearBoxType");

            if (gearBoxType != null) {
                String enumGearBox = gearBoxType.toUpperCase().replace(" ", "_");
                Expression gearBoxTypeExpression = Expression.builder()
                        .expression("#gearBoxType = :gearBoxType")
                        .expressionNames(Map.of("#gearBoxType", "CAR#GEAR_BOX_TYPE"))
                        .expressionValues(Map.of(":gearBoxType", AttributeValue.builder().s(enumGearBox).build()))
                        .build();
                expressions.add(gearBoxTypeExpression);
            }

            LogPrinter.info("[CarPageRequest] gearBoxType in expression {}", CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder fuelTypeEquals() {
            String fuelType = params.get("fuelType");

            if (fuelType != null) {
                String enumFuel = fuelType.toUpperCase().replace(" ", "_");
                Expression fuelTypeExpression = Expression.builder()
                        .expression("#fuelType = :fuelType")
                        .expressionNames(Map.of("#fuelType", "CAR#FUEL_TYPE"))
                        .expressionValues(Map.of(":fuelType", AttributeValue.builder().s(enumFuel).build()))
                        .build();
                expressions.add(fuelTypeExpression);
            }

            LogPrinter.info("[CarPageRequest] fuelType in expression {}",
                    CarPageRequest.this.expressions.size());
            return this;
        }

        public Builder priceInBetweenMinAndMaxPrices() {
            String minPrice = params.get("minPrice");
            String maxPrice = params.get("maxPrice");

            if (minPrice == null || !isPositiveInteger(minPrice)) {
                minPrice = String.valueOf(DEFAULT_MIN_PRICE);
            }

            if (maxPrice == null || !isPositiveInteger(maxPrice)) {
                maxPrice = String.valueOf(DEFAULT_MAX_PRICE);
            }

            Expression priceExpression = Expression.builder()
                    .expression("#price BETWEEN :minPrice AND :maxPrice")
                    .expressionNames(Map.of("#price", "CAR#PRICE_PER_DAY"))
                    .expressionValues(Map.of(
                            ":minPrice", AttributeValue.builder().n(minPrice).build(),
                            ":maxPrice", AttributeValue.builder().n(maxPrice).build()))
                    .build();
            expressions.add(priceExpression);

            LogPrinter.info("[CarPageRequest] minPrice & maxPrice in expression {}",
                    CarPageRequest.this.expressions.size());
            return this;
        }

        public CarPageRequest build() {
            LogPrinter.info("[CarPageRequest] Final expression size {}", CarPageRequest.this.expressions.size());
            CarPageRequest.this.filter = Expression.join("AND", CarPageRequest.this.expressions);
            return CarPageRequest.this;
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
package com.car_rent_api.persistence.pagination.api;

import com.car_rent_api.persistence.pagination.type.JoinType;
import com.car_rent_api.persistence.pagination.type.ValueType;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SpecificationRequest {

    private List<Expression> expressions;
    private Expression filter;

    public SpecificationRequest() {
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public Expression getFilter() {
        return filter;
    }

    public Builder toBuilder() {
        return this.new Builder();
    }

    public static Builder builder() {
        return new SpecificationRequest().new Builder();
    }

    protected static class AttributeValueBuilder {

        public AttributeValueBuilder() {
        }

        public static AttributeValue buildAttribute(ValueType type, String value) {
            return switch (type) {
                case STRING -> AttributeValue.builder().s(value).build();
                case STRING_UPPERCASE -> AttributeValue.builder().s(value.toUpperCase()).build();
                case STRING_LOWERCASE -> AttributeValue.builder().s(value.toLowerCase()).build();
                case NUMBER -> AttributeValue.builder().n(value).build();
            };
        }
    }

    public class Builder {

        private final AtomicInteger memberCount;

        private Builder() {
            this.memberCount = new AtomicInteger(0);
            SpecificationRequest.this.expressions = new ArrayList<>();
        }

        public Builder equalTo(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} = :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder notEqualTo(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} <> :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder lessThanOrEqualTo(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} <= :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder lessThan(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} < :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder greaterThanOrEqualTo(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} >= :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder greaterThan(ValueType type, String name, String value) {
            String expr = "#attribute{modCount} > :attribute{modCount}";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder between(ValueType type, String name, String minValue, String maxValue) {
            String expr = "#attribute{modCount} BETWEEN :minValue{modCount} AND :maxValue{modCount}";
            buildBinaryExpression(expr, type, name, minValue, maxValue);
            return this;
        }

        public Builder exists(ValueType type, String name, String value) {
            String expr = "attribute_exists(#attribute{modCount})";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder notExists(ValueType type, String name, String value) {
            String expr = "attribute_not_exists(#attribute{modCount})";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder contains(ValueType type, String name, String value) {
            String expr = "contains(#attribute{modCount}, :attribute{modCount})";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder notContains(ValueType type, String name, String value) {
            String expr = "NOT contains(#attribute{modCount}, :attribute{modCount})";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        public Builder beginsWith(ValueType type, String name, String value) {
            String expr = "begins_with(#attribute{modCount}, :attribute{modCount})";
            buildUnaryExpression(expr, type, name, value);
            return this;
        }

        //JOIN TYPE OR
        public Builder allInListRange(ValueType valueType, String name, List<String> range) {
            String expr = "contains(#attribute{modCount}, :attribute{modCount})";

            if (range != null && !range.isEmpty()) {
                range.forEach(s -> {
                    buildUnaryExpression(expr, valueType, name, s);
                    memberCount.getAndIncrement();
                });
            }

            return this;
        }

        //JOIN TYPE OR
        public Builder allNotInListRange(ValueType valueType, String name, List<String> range) {
            String expr = "NOT contains(#attribute{modCount}, :attribute{modCount})";

            if (range != null && !range.isEmpty()) {
                range.forEach(s -> {
                    buildUnaryExpression(expr, valueType, name, s);
                    memberCount.getAndIncrement();
                });
            }

            return this;
        }

        public SpecificationRequest build(JoinType joinType) {
            SpecificationRequest.this.filter = Expression.join(joinType.name(), SpecificationRequest.this.expressions);
            return SpecificationRequest.this;
        }

        private void buildUnaryExpression(String expr, ValueType type, String name, String value) {
            if (value != null) {
                expr = expr.replace("{modCount}", memberCount.toString());
                Map<String, String> names = Map.of("#attribute" + memberCount, name);
                Map<String, AttributeValue> vals =
                        Map.of(":attribute" + memberCount, AttributeValueBuilder.buildAttribute(type, value));
                Expression unaryExpression = Expression.builder()
                        .expression(expr)
                        .expressionNames(names)
                        .expressionValues(vals)
                        .build();
                SpecificationRequest.this.expressions.add(unaryExpression);
                memberCount.getAndIncrement();
            }
        }

        private void buildBinaryExpression(String expr, ValueType type, String name,
                                           String valueOne, String valueTwo) {
            if (valueOne != null && valueTwo != null) {
                expr = expr.replace("{modCount}", memberCount.toString());
                Map<String, String> names = Map.of("#attribute" + memberCount, name);
                Map<String, AttributeValue> vals = Map.of(
                        ":minValue" + memberCount, AttributeValueBuilder.buildAttribute(type, valueOne),
                        ":maxValue" + memberCount, AttributeValueBuilder.buildAttribute(type, valueTwo));
                Expression binaryExpression =
                        Expression.builder().expression(expr).expressionNames(names).expressionValues(vals).build();
                SpecificationRequest.this.expressions.add(binaryExpression);
                memberCount.getAndIncrement();
            }
        }
    }
}
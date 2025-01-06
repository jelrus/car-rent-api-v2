package com.car_rent_api.utils.components;

public class TransactionContext {

    private static final ThreadLocal<String> transactionId = new ThreadLocal<>();

    public static void setTransactionId(String id) {
        transactionId.set(id);
    }

    public static String getTransactionId() {
        return transactionId.get();
    }

    public static void clear() {
        transactionId.remove();
    }
}

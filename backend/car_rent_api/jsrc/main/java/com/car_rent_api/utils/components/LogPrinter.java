package com.car_rent_api.utils.components;

import com.car_rent_api.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogPrinter {

    private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

    public static void info(String message, Object ... objects) {
        String infoMessage = "[" + TransactionContext.getTransactionId() + "]" + message;
        logger.info(infoMessage, objects);
    }

    public static void warn(String message, Object ... objects) {
        String warnMessage = "[" + TransactionContext.getTransactionId() + "]" + message;
        logger.warn(warnMessage, objects);
    }

    public static void error(String message, Object ... objects) {
        String errorMessage = "[" + TransactionContext.getTransactionId() + "]" + message;
        logger.error(errorMessage, objects);
    }
}
package com.backend.utils.services;

import com.backend.ApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerService is the utility class, which serves as the main logger for application
 */
public class LoggerService {

    /**
     * Represents logger for main application class
     */
    private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

    /**
     * Logs message and objects with status info to the log
     *
     * @param message {@code String} message to log
     * @param objects {@code Object...} objects inserted to the message
     */
    public static void info(String message, Object ... objects) {
        logger.info(message, objects);
    }

    /**
     * Logs message and objects with status warn to the log
     *
     * @param message {@code String} message to log
     * @param objects {@code Object...} objects inserted to the message
     */
    public static void warn(String message, Object ... objects) {
        logger.warn(message, objects);
    }

    /**
     * Logs message and objects with status error to the log
     *
     * @param message {@code String} message to log
     * @param objects {@code Object...} objects inserted to the message
     */
    public static void error(String message, Object ... objects) {
        logger.error(message, objects);
    }
}
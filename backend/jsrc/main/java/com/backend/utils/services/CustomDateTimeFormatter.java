package com.backend.utils.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CustomDateTimeFormatter is the utility class, which provides DateTimeFormatter of pattern "yyyy-MM-dd HH:mm"
 */
public class CustomDateTimeFormatter {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String convertDateTimeToDate(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter).toLocalDate().toString();
    }
}
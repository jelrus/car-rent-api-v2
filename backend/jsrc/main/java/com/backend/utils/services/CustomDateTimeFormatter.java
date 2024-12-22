package com.backend.utils.services;

import java.time.format.DateTimeFormatter;

/**
 * CustomDateTimeFormatter is the utility class, which provides DateTimeFormatter of pattern "yyyy-MM-dd HH:mm"
 */
public class CustomDateTimeFormatter {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
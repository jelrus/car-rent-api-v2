package com.car_rent_api.utils.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class StringDateConverter {

    private static final String GERMAN_DATE_TIME = "dd.MM.yyyy HH:mm"; //29.10.2024 10:30
    private static final String GERMAN_DATE = "dd.MM.yyyy"; //29.10.2024
    private static final String ISO8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss"; //2024-10-29T10:30:00
    private static final String ISO8601_DATE = "dd-MM-yyyy";

    public static boolean isISO8601DateTime(String value) {
        try {
            LocalDateTime.parse(value).format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String toISO8601DateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
    }

    public static String toISO8601DateTime(String dateTime) {
        return LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
    }

    public static boolean isISO8601DateTimeStartBeforeEnd(String start, String end) {
        return fromStringToISO8601DateTime(start).isBefore(fromStringToISO8601DateTime(end));
    }

    public static LocalDateTime fromStringToISO8601DateTime(String dateTime) {
        String convertDate = LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
        return LocalDateTime.parse(convertDate);
    }

    public static LocalDate fromISO8601DateTimeToDate(String dateTime) {
        return fromStringToISO8601DateTime(dateTime).toLocalDate();
    }

    public static String fromISO8601ToGermanDateString(LocalDate iso8601Date) {
        return iso8601Date.format(DateTimeFormatter.ofPattern(GERMAN_DATE));
    }

    public static List<String> generateGermanDatesRange(String pickupDate, String dropOffDate) {
        LocalDate dateA = fromISO8601DateTimeToDate(pickupDate);
        LogPrinter.info("date A {}", dateA.toString());
        LocalDate dateB = fromISO8601DateTimeToDate(dropOffDate);
        LogPrinter.info("date B {}", dateB.toString());
        List<String> datesRange = new ArrayList<>();

        while (dateA.isBefore(dateB)) {
            datesRange.add(fromISO8601ToGermanDateString(dateA));
            dateA = dateA.plusDays(1);
            LogPrinter.info("date A {}, date B {}", dateA.toString(), dateB.toString());
        }

        datesRange.add(fromISO8601ToGermanDateString(dateB));
        LogPrinter.info("Dates range {}", datesRange.toString());

        return datesRange;
    }
}
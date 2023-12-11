package com.mslfox.oniipswingjpamvc.utility;

import java.time.LocalDateTime;

public class DateTimeFormatter {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private DateTimeFormatter() {
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Validation {
    public Validation() {}


    public static void validateDates(LocalDate startDate, LocalDate endDate){
        Objects.requireNonNull(startDate, "Start date cannot be null");
        Objects.requireNonNull(endDate, "End date cannot be null");
        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException("End date cannot be before start date");
    }

    public static void validateTime(LocalTime startTime, LocalTime endTime){
        Objects.requireNonNull(startTime, "Start time cannot be null");
        Objects.requireNonNull(endTime, "End time cannot be null");
        if (endTime.isBefore(startTime))
            throw new IllegalArgumentException("End time cannot be before start time");
    }


    public static void validateString(String value, String fieldName){
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Reason cannot be empty or null.");
        }
    }

    public static void validateBigDecimal(BigDecimal value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }
}

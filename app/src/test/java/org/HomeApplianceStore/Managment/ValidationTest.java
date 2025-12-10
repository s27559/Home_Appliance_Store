package org.HomeApplianceStore.Managment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    // --- Date Tests ---
    @Test
    void datesAreValid() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 2);
        assertDoesNotThrow(() -> Validation.validateDates(start, end));
    }

    @Test
    void startDateCannotBeNull() {
        LocalDate end = LocalDate.of(2025, 1, 2);
        NullPointerException ex = assertThrows(NullPointerException.class, () -> Validation.validateDates(null, end));
        assertTrue(ex.getMessage().contains("Start date cannot be null"));
    }

    @Test
    void endDateCannotBeBeforeStart() {
        LocalDate start = LocalDate.of(2025, 1, 10);
        LocalDate end = LocalDate.of(2025, 1, 1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validation.validateDates(start, end));
        assertEquals("End date cannot be before start date", ex.getMessage());
    }

    // --- Time Tests ---
    @Test
    void timesAreValid() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        assertDoesNotThrow(() -> Validation.validateTime(start, end));
    }

    @Test
    void endTimeCannotBeNull() {
        LocalTime start = LocalTime.of(9, 0);
        NullPointerException ex = assertThrows(NullPointerException.class, () -> Validation.validateTime(start, null));
        assertTrue(ex.getMessage().contains("End time cannot be null"));
    }

    @Test
    void endTimeCannotBeBeforeStart() {
        LocalTime start = LocalTime.of(18, 0);
        LocalTime end = LocalTime.of(9, 0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validation.validateTime(start, end));
        assertEquals("End time cannot be before start time", ex.getMessage());
    }

    // --- String Tests ---
    @Test
    void stringIsValid() {
        assertDoesNotThrow(() -> Validation.validateString("some value", "reason"));
    }

    @Test
    void stringCannotBeNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validation.validateString(null, "reason"));
        assertEquals("Reason cannot be empty or null.", ex.getMessage());
    }

    @Test
    void stringCannotBeEmptyOrSpace() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> Validation.validateString("", "reason"));
        assertEquals("Reason cannot be empty or null.", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> Validation.validateString("   ", "reason"));
        assertEquals("Reason cannot be empty or null.", ex2.getMessage());
    }

    // --- BigDecimal Tests ---
    @Test
    void priceCannotBeNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validation.validateBigDecimal(null, "price"));
        assertEquals("price cannot be null.", ex.getMessage());
    }

    @Test
    void priceCannotBeNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Validation.validateBigDecimal(new BigDecimal("-1"), "price"));
        assertEquals("price cannot be negative.", ex.getMessage());
    }

    @Test
    void priceCanBeZeroOrPositive() {
        assertDoesNotThrow(() -> Validation.validateBigDecimal(BigDecimal.ZERO, "price"));
        assertDoesNotThrow(() -> Validation.validateBigDecimal(new BigDecimal("0.01"), "price"));
    }
}
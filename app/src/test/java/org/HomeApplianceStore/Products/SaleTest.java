package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    private static final String VALID_NAME = "Summer Sale";
    private static final LocalDate START_DATE = LocalDate.of(2025, 6, 1);
    private static final LocalDate END_DATE = LocalDate.of(2025, 6, 30);
    private static final BigDecimal VALID_AMOUNT = new BigDecimal("15.996");
    private static final BigDecimal EXPECTED_SCALED_AMOUNT = new BigDecimal("16.00");

    @Test
    void constructor_works_andInitializesFields_whenValid() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);

        assertEquals(VALID_NAME, sale.getName());
        assertEquals(START_DATE, sale.getStartDate());
        assertEquals(END_DATE, sale.getEndDate());
        assertEquals(EXPECTED_SCALED_AMOUNT, sale.getAmount());
    }

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Sale(null, START_DATE, END_DATE, VALID_AMOUNT);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenAmountIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sale(VALID_NAME, START_DATE, END_DATE, BigDecimal.ZERO);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenEndDateIsBeforeStartDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sale(VALID_NAME, END_DATE, START_DATE, VALID_AMOUNT);
        });
    }

    @Test
    void setAmount_throwsIllegalArgumentException_whenNegative() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);

        assertThrows(IllegalArgumentException.class, () -> sale.setAmount(new BigDecimal("-0.01")));

        assertEquals(EXPECTED_SCALED_AMOUNT, sale.getAmount());
    }

    @Test
    void setStartDate_works_whenValid() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);
        LocalDate newDate = START_DATE.minusDays(5);

        sale.setStartDate(newDate);

        assertEquals(newDate, sale.getStartDate());
    }

    @Test
    void setStartDate_throwsIllegalArgumentException_whenAfterEndDate() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);
        LocalDate invalidDate = END_DATE.plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> sale.setStartDate(invalidDate));

        assertEquals(START_DATE, sale.getStartDate());
    }

    @Test
    void setEndDate_works_whenValid() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);
        LocalDate newDate = END_DATE.plusDays(5);

        sale.setEndDate(newDate);

        assertEquals(newDate, sale.getEndDate());
    }

    @Test
    void setEndDate_throwsIllegalArgumentException_whenBeforeStartDate() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);
        LocalDate invalidDate = START_DATE.minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> sale.setEndDate(invalidDate));

        assertEquals(END_DATE, sale.getEndDate());
    }

    @Test
    void getPeriodDays_returnsCorrectDifference() {
        Sale sale = new Sale(VALID_NAME, START_DATE, END_DATE, VALID_AMOUNT);

        // 2025-06-01 until 2025-06-30 is 29 days (since until is exclusive of the end day)
        long expectedDays = 29;

        assertEquals(expectedDays, sale.getPeriodDays());
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnAllFields() {
        Sale s1 = new Sale(VALID_NAME, START_DATE, END_DATE, new BigDecimal("10.00"));
        Sale s2 = new Sale(VALID_NAME, START_DATE, END_DATE, new BigDecimal("10.00"));
        Sale s3 = new Sale("Holiday Sale", START_DATE, END_DATE, new BigDecimal("10.00"));

        assertTrue(s1.equals(s2));
        assertEquals(s1.hashCode(), s2.hashCode());

        assertFalse(s1.equals(s3));
    }
}
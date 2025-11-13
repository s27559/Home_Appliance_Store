package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClosedForTest {

    @Test
    void testConstructor() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 2);
        String reason = "Renovation";

        ClosedFor event = new ClosedFor(start, end, reason, new ArrayList<Store>());

        assertTrue(reason.equals(event.getReason()));
        assertTrue(start.equals(event.getStartDate()));
    }

    @Test
    void testPeriodDays() {
        LocalDate start = LocalDate.of(2024, 5, 10);
        LocalDate end = LocalDate.of(2024, 5, 20);

        ClosedFor event = new ClosedFor(start, end, "Inventory", null);

        Long expected = 10L;
        assertTrue(expected.equals(event.getPeriodDays()));
    }
}
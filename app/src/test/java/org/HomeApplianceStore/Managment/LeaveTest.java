package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LeaveTest {
    @Test
    void constructorAddsToExtentAndSetsAttributes() {
        LocalDate start = LocalDate.of(3000, 1, 1);
        LocalDate end = LocalDate.of(3000, 1, 5);

        Leave leave = new Leave(true, false, start, end);

        assertTrue(leave.isSick());
        assertFalse(leave.isPaid());
        assertEquals(start, leave.getStartDate());
        assertEquals(end, leave.getEndDate());

        List<Leave> extent = Leave.getLeaves();
        assertTrue(extent.contains(leave));
    }

    @Test
    void periodDaysCalculationUsesDaysBetween() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 10);

        Leave leave = new Leave(false, true, start, end);

        long expected = ChronoUnit.DAYS.between(start, end);
        assertEquals(expected, leave.getPeriodDays());
    }

    @Test
    void constructorThrowsOnEndBeforeStart() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDate end = LocalDate.of(2024, 6, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            new Leave(false, false, start, end);
        });
    }

    @Test
    void persistenceRoundTripLoadsSavedLeave() throws Exception {
        LocalDate start = LocalDate.of(4000, 1, 1);
        LocalDate end = LocalDate.of(4000, 1, 2);
        Leave leave = new Leave(true, true, start, end);

        // save
        Leave.saveLeaves();

        // clear
        Field f = Leave.class.getDeclaredField("leaves");
        f.setAccessible(true);
        f.set(null, new ArrayList<Leave>());

        // ensure cleared
        assertTrue(Leave.getLeaves().isEmpty());

        // load
        Leave.loadLeaves();
        boolean found = Leave.getLeaves().stream()
                .anyMatch(l -> l.isSick() == leave.isSick()
                        && l.isPaid() == leave.isPaid()
                        && start.equals(l.getStartDate())
                        && end.equals(l.getEndDate()));
        assertTrue(found, "Persisted Leave should be loaded into extent");
    }
}

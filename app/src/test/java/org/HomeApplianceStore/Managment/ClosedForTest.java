package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClosedForTest {

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 3);
        String reason = "Renovation";
        ArrayList<Store> stores = new ArrayList<>();

        ClosedFor event = new ClosedFor(start, end, reason);

        assertEquals(reason, event.getReason());
        assertEquals(start, event.getStartDate());
        assertEquals(end, event.getEndDate());
        List<ClosedFor> extent = ClosedFor.getClosedForEvents();
        assertTrue(extent.contains(event));
    }

    @Test
    void testPeriodDaysCalculation() {
        LocalDate start = LocalDate.of(2024, 5, 10);
        LocalDate end = LocalDate.of(2024, 5, 20);

        ClosedFor event = new ClosedFor(start, end, "Inventory");

        long expected = 10L;
        assertEquals(expected, event.getPeriodDays());
    }

    @Test
    void testConstructorThrowsOnInvalidDateRange() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDate end = LocalDate.of(2024, 6, 5); // end before start

        assertThrows(IllegalArgumentException.class, () -> {
            new ClosedFor(start, end, "Invalid range");
        });
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        LocalDate start = LocalDate.of(2024, 7, 1);
        LocalDate end = LocalDate.of(2024, 7, 2);
        String uniqueReason = "PersistTest-" + System.currentTimeMillis();

        ClosedFor event = new ClosedFor(start, end, uniqueReason);

        ClosedFor.saveClosedForEvents();

        // clear in memory
        Field f = ClosedFor.class.getDeclaredField("closedForEvents");
        f.setAccessible(true);
        f.set(null, new ArrayList<ClosedFor>());

        // confirm
        assertTrue(ClosedFor.getClosedForEvents().isEmpty());

        // load from persistence and verify event is restored
        ClosedFor.loadClosedForEvents();
        boolean found = ClosedFor.getClosedForEvents().stream()
                .anyMatch(e -> uniqueReason.equals(e.getReason())
                        && start.equals(e.getStartDate())
                        && end.equals(e.getEndDate()));
        assertTrue(found, "Persisted ClosedFor event should be loaded into extent");
    }
}

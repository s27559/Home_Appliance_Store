package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedForTest {

    private Store createDummyStore() {
        Address address = new Address();
        return new Store(address);
    }

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 3);
        String reason = "Renovation";

        Store store = createDummyStore();

        ClosedFor event = new ClosedFor(start, end, reason, store);

        assertEquals(reason, event.getReason());
        assertEquals(start, event.getStartDate());
        assertEquals(end, event.getEndDate());

        assertEquals(store, event.getStore());

        List<ClosedFor> extent = ClosedFor.getClosedForEvents();
        assertTrue(extent.contains(event));
    }

    @Test
    void testPeriodDaysCalculation() {
        LocalDate start = LocalDate.of(2024, 5, 10);
        LocalDate end = LocalDate.of(2024, 5, 20);

        Store store = createDummyStore();

        ClosedFor event = new ClosedFor(start, end, "Inventory", store);

        long expected = 10L;
        assertEquals(expected, event.getPeriodDays());
    }

    @Test
    void testConstructorThrowsOnInvalidDateRange() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDate end = LocalDate.of(2024, 6, 5); // end before start

        Store store = createDummyStore();

        assertThrows(IllegalArgumentException.class, () -> {
            new ClosedFor(start, end, "Invalid range", store);
        });
    }

    @Test
    void testConstructorThrowsOnNullStore() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 5);

        assertThrows(NullPointerException.class, () -> { // Or IllegalArgumentException depending on your Validation
            new ClosedFor(start, end, "No Store", null);
        });
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        LocalDate start = LocalDate.of(2024, 7, 1);
        LocalDate end = LocalDate.of(2024, 7, 2);
        String uniqueReason = "PersistTest-" + System.currentTimeMillis();

        Store store = createDummyStore();

        ClosedFor event = new ClosedFor(start, end, uniqueReason, store);

        ClosedFor.saveClosedForEvents();

        // clear in memory via reflection to simulate application restart
        Field f = ClosedFor.class.getDeclaredField("closedForEvents");
        f.setAccessible(true);
        f.set(null, new ArrayList<ClosedFor>());

        // confirm extent is empty in memory
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
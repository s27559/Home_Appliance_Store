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

    // -- BYT 6 --
    @Test
    void testAddClosedForCreatesReverseConnection() {
        Store store = createDummyStore();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(3);

        // Create ClosedFor (Composition)
        ClosedFor event = new ClosedFor(start, end, "Renovation", store);

        // Check Forward Connection
        assertEquals(store, event.getStore());

        // Check Reverse Connection
        assertTrue(store.getClosedForEvents().contains(event),
                "Store should contain the ClosedFor event created with it");
    }

    @Test
    void testModifyClosedForMovesAssociation() {
        Store store = createDummyStore();
        Store newStore = createDummyStore();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(3);
        ClosedFor event = new ClosedFor(start, end, "Renovation", store);

        assertTrue(store.getClosedForEvents().contains(event));
        assertFalse(newStore.getClosedForEvents().contains(event));

        event.setStore(newStore);

        // Verify Reverse Connection update
        assertFalse(store.getClosedForEvents().contains(event), "Old store should no longer have the event");
        assertTrue(newStore.getClosedForEvents().contains(event), "New store should now have the event");
        assertEquals(newStore, event.getStore());
    }

    @Test
    void testDeleteRemovesReverseConnection() {
        Store store = createDummyStore();
        ClosedFor event = new ClosedFor(LocalDate.now(), LocalDate.now().plusDays(1), "Holiday", store);

        assertTrue(store.getClosedForEvents().contains(event));

        event.delete();
        assertFalse(store.getClosedForEvents().contains(event), "Deleting ClosedFor should remove it from Store");
    }

    @Test
    void testErrorHandlingNullStore() {
        assertThrows(NullPointerException.class, () -> {
            new ClosedFor(LocalDate.now(), LocalDate.now().plusDays(1), "Fail", null);
        });
    }
}
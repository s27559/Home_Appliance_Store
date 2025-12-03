package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShiftTest {

    private static class TestShift extends Shift {
        public TestShift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, Store store) {
            super(bonusPay, openTime, closeTime, store);
        }
    }

    private Store createDummyStore() {
        return new Store(new Address());
    }

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() throws Exception {
        BigDecimal bonus = new BigDecimal("50.00");
        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(16, 0);
        Store store = createDummyStore();

        // Use the concrete subclass
        Shift shift = new TestShift(bonus, open, close, store);

        assertEquals(bonus, shift.getBonusPay());
        assertEquals(open, shift.getOpenTime());
        assertEquals(close, shift.getCloseTime());
        assertEquals(store, shift.getStore());

        // Verify extent addition via reflection
        Field field = Shift.class.getDeclaredField("shifts");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<Shift> shifts = (ArrayList<Shift>) field.get(null);

        assertTrue(shifts.contains(shift), "Shift should be added to the static extent list");
    }

    @Test
    void testSettersUpdateAttributes() {
        Store store = createDummyStore();
        Shift shift = new TestShift(BigDecimal.ZERO, LocalTime.MIN, LocalTime.MAX, store);

        BigDecimal newBonus = new BigDecimal("75.00");
        LocalTime newOpen = LocalTime.of(10, 0);

        shift.setBonusPay(newBonus);
        shift.setOpenTime(newOpen);

        assertEquals(newBonus, shift.getBonusPay());
        assertEquals(newOpen, shift.getOpenTime());
    }
}
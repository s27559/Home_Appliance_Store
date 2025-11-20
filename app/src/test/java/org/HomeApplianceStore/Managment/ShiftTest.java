package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShiftTest {

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() throws Exception {
        BigDecimal bonus = new BigDecimal("50.00");
        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(16, 0);

        Shift shift = new Shift(bonus, open, close);

        assertEquals(bonus, shift.getBonusPay());
        assertEquals(open, shift.getOpenTime());
        assertEquals(close, shift.getCloseTime());

        // Verify extent addition via reflection since getShifts() is missing in base class
        Field field = Shift.class.getDeclaredField("shifts");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<Shift> shifts = (ArrayList<Shift>) field.get(null);

        assertTrue(shifts.contains(shift), "Shift should be added to the static extent list");
    }

    @Test
    void testSettersUpdateAttributes() {
        Shift shift = new Shift(BigDecimal.ZERO, LocalTime.MIN, LocalTime.MAX);

        BigDecimal newBonus = new BigDecimal("75.00");
        LocalTime newOpen = LocalTime.of(10, 0);

        shift.setBonusPay(newBonus);
        shift.setOpenTime(newOpen);

        assertEquals(newBonus, shift.getBonusPay());
        assertEquals(newOpen, shift.getOpenTime());
    }
}
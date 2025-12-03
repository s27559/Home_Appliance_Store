package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HolidayShiftTest {

    private Store createDummyStore() {
        return new Store(new Address());
    }

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        BigDecimal bonus = new BigDecimal("100.00");
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(17, 0);
        LocalDate start = LocalDate.of(2024, 12, 24);
        LocalDate end = LocalDate.of(2024, 12, 26);
        Store store = createDummyStore();

        HolidayShift shift = new HolidayShift(bonus, open, close, start, end, store);

        assertEquals(bonus, shift.getBonusPay());
        assertEquals(open, shift.getOpenTime());
        assertEquals(close, shift.getCloseTime());
        assertEquals(start, shift.getStartDate());
        assertEquals(end, shift.getEndDate());
        assertEquals(store, shift.getStore());

        List<HolidayShift> extent = HolidayShift.getHolidayShifts();
        assertTrue(extent.contains(shift));
    }

    @Test
    void testPeriodDaysCalculation() {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 5);
        Store store = createDummyStore();

        HolidayShift shift = new HolidayShift(BigDecimal.ZERO, LocalTime.NOON, LocalTime.NOON, start, end, store);

        long expected = 4L;
        assertEquals(expected, shift.getPeriodDays());
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        BigDecimal uniqueBonus = new BigDecimal("999.99");
        Store store = createDummyStore();

        HolidayShift shift = new HolidayShift(uniqueBonus, LocalTime.MIN, LocalTime.MAX, LocalDate.now(), LocalDate.now().plusDays(1), store);

        HolidayShift.saveHolidayShifts();

        // Clear
        Field f = HolidayShift.class.getDeclaredField("holidayShifts");
        f.setAccessible(true);
        f.set(null, new ArrayList<HolidayShift>());

        assertTrue(HolidayShift.getHolidayShifts().isEmpty());

        HolidayShift.loadHolidayShifts();
        boolean found = HolidayShift.getHolidayShifts().stream()
                .anyMatch(s -> uniqueBonus.equals(s.getBonusPay()));

        assertTrue(found, "Persisted HolidayShift should be loaded into extent");
    }
}
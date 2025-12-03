package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeekdayShiftTest {

    private Store createDummyStore() {
        return new Store(new Address());
    }

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        BigDecimal bonus = new BigDecimal("20.00");
        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(16, 0);
        DayOfWeek day = DayOfWeek.MONDAY;
        Store store = createDummyStore();

        WeekdayShift shift = new WeekdayShift(bonus, open, close, day, store);

        assertEquals(bonus, shift.getBonusPay());
        assertEquals(open, shift.getOpenTime());
        assertEquals(close, shift.getCloseTime());
        assertEquals(day, shift.getWeekday());
        assertEquals(store, shift.getStore());

        List<WeekdayShift> extent = WeekdayShift.getWeekdayShifts();
        assertTrue(extent.contains(shift));
    }

    @Test
    void testSetterUpdatesAttribute() {
        Store store = createDummyStore();
        WeekdayShift shift = new WeekdayShift(BigDecimal.ZERO, LocalTime.MIN, LocalTime.MAX, DayOfWeek.FRIDAY, store);

        shift.setWeekday(DayOfWeek.SATURDAY);
        assertEquals(DayOfWeek.SATURDAY, shift.getWeekday());
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        BigDecimal uniqueBonus = new BigDecimal("888.88");
        Store store = createDummyStore();

        WeekdayShift shift = new WeekdayShift(uniqueBonus, LocalTime.of(13, 20), LocalTime.of(23, 20), DayOfWeek.WEDNESDAY, store);

        WeekdayShift.saveWeekdayShifts();

        // clear
        Field f = WeekdayShift.class.getDeclaredField("weekdayShifts");
        f.setAccessible(true);
        f.set(null, new ArrayList<WeekdayShift>());

        assertTrue(WeekdayShift.getWeekdayShifts().isEmpty());

        WeekdayShift.loadWeekdayShifts();
        boolean found = WeekdayShift.getWeekdayShifts().stream()
                .anyMatch(s -> uniqueBonus.equals(s.getBonusPay())
                        && DayOfWeek.WEDNESDAY.equals(s.getWeekday()));

        assertTrue(found, "Persisted WeekdayShift should be loaded into extent");
    }
}
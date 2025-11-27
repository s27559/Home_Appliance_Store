package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeekdayShiftTest {

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        BigDecimal bonus = new BigDecimal("20.00");
        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(16, 0);
        DayOfWeek day = DayOfWeek.MONDAY;

        WeekdayShift shift = new WeekdayShift(bonus, open, close, day);

        assertEquals(bonus, shift.getBonusPay());
        assertEquals(open, shift.getOpenTime());
        assertEquals(close, shift.getCloseTime());
        assertEquals(day, shift.getWeekday());

        List<WeekdayShift> extent = WeekdayShift.getWeekdayShifts();
        assertTrue(extent.contains(shift));
    }

    @Test
    void testSetterUpdatesAttribute() {
        WeekdayShift shift = new WeekdayShift(BigDecimal.ZERO, LocalTime.MIN, LocalTime.MAX, DayOfWeek.FRIDAY);

        shift.setWeekday(DayOfWeek.SATURDAY);
        assertEquals(DayOfWeek.SATURDAY, shift.getWeekday());
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        BigDecimal uniqueBonus = new BigDecimal("888.88");
        WeekdayShift shift = new WeekdayShift(uniqueBonus, LocalTime.of(13, 20), LocalTime.of(23, 20), DayOfWeek.WEDNESDAY);

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
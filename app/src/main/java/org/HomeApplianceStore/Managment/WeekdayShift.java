package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeekdayShift extends Shift implements Extent {

        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/WeekdayShift.ser";
        private static ArrayList<WeekdayShift> weekdayShifts = new ArrayList<>();

        private DayOfWeek weekday;

        public WeekdayShift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, DayOfWeek weekday, ArrayList<Store> store) {
                super(bonusPay, openTime, closeTime, store);
                Validation.validateBigDecimal(bonusPay, "Bonus Pay");
                Objects.requireNonNull(weekday);
                this.weekday = weekday;
                addWeekdayShift(this);
                saveWeekdayShifts();
        }

        private static void addWeekdayShift(WeekdayShift shift) {
            if (!weekdayShifts.contains(shift))
                weekdayShifts.add(shift);
            saveWeekdayShifts();
        }

        public DayOfWeek getWeekday() {
                return weekday;
        }

        public void setWeekday(DayOfWeek weekday) {
                this.weekday = weekday;
                saveWeekdayShifts();
        }

        // extend methods
        public static void loadWeekdayShifts() {
            List<WeekdayShift> loaded = Extent.loadClassList(FILE_LOCATION);
            weekdayShifts = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
        }

        public static void saveWeekdayShifts() {
                Extent.saveClassList(FILE_LOCATION, weekdayShifts);
        }

        public static List<WeekdayShift> getWeekdayShifts() {
                return Extent.getImmutableClassList(weekdayShifts);
        }

        public void delete() {
                weekdayShifts.remove(this);
                saveWeekdayShifts();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WeekdayShift)) return false;
            if (!super.equals(o)) return false;
            WeekdayShift other = (WeekdayShift) o;
            return Objects.equals(weekday, other.weekday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), weekday);
        }
}

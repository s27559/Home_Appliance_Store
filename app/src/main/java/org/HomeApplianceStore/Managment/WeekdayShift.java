package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeekdayShift extends Shift implements Extent {

        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/WeekdayShift.ser";
        private static ArrayList<WeekdayShift> weekdayShifts = new ArrayList<>();

        private DayOfWeek weekday;

        public WeekdayShift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, DayOfWeek weekday) {
                super(bonusPay, openTime, closeTime);
                this.weekday = weekday;
                addWeekdayShift(this);
        }

        private static void addWeekdayShift(WeekdayShift shift) {
                weekdayShifts.add(shift);
        }

        public DayOfWeek getWeekday() {
                return weekday;
        }

        public void setWeekday(DayOfWeek weekday) {
                this.weekday = weekday;
        }

        // extend methods
        public static void loadWeekdayShifts() {
                weekdayShifts = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveWeekdayShifts() {
                Extent.saveClassList(FILE_LOCATION, weekdayShifts);
        }

        public static List<WeekdayShift> getWeekdayShifts() {
                return Extent.getImmutableClassList(weekdayShifts);
        }

        public void delete() {
                weekdayShifts.remove(this);
        }
}

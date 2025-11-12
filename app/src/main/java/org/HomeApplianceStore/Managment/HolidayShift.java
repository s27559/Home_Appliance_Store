package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidayShift extends Shift implements Extent {
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/HolidayShift.ser";
        private static ArrayList<HolidayShift> holidayShifts = new ArrayList<>();

        private LocalDate startDate;
        private LocalDate endDate;

        public HolidayShift() {
                super();
                addHolidayShift(this);
        }

        private static void addHolidayShift(HolidayShift shift) {
                holidayShifts.add(shift);
        }

        // preiodDays
        public LocalDate getStartDate() {
                return startDate;
        }
        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }

        // extend methods
        public static void loadHolidayShifts() {
                holidayShifts = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveHolidayShifts() {
                Extent.saveClassList(FILE_LOCATION, holidayShifts);
        }

        public static List<HolidayShift> getHolidayShifts() {
                return Extent.getImmutableClassList(holidayShifts);
        }

        public void delete() {
                holidayShifts.remove(this);
        }
}

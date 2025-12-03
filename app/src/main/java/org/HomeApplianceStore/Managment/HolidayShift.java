package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Actors.Employee;
import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HolidayShift extends Shift implements Extent {
        private static final String FILE_LOCATION = "HolidayShift.ser";
        private static ArrayList<HolidayShift> holidayShifts = new ArrayList<>();

        static {
                loadHolidayShifts();
        }

        private LocalDate startDate;
        private LocalDate endDate;

        public HolidayShift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, LocalDate startDate, LocalDate endDate, Store store, Employee employee) {
                super(bonusPay, openTime, closeTime, store, employee);
                Validation.validateDates(startDate, endDate);
                Validation.validateBigDecimal(bonusPay, "Bonus Pay");
                this.startDate = startDate;
                this.endDate = endDate;
                addHolidayShift(this);
                saveHolidayShifts();
        }

        public long getPeriodDays() {
            return startDate.until(endDate).getDays();
        }

        private static void addHolidayShift(HolidayShift shift) {
            if (!holidayShifts.contains(shift))
                holidayShifts.add(shift);
        }

        // preiodDays
        public LocalDate getStartDate() {
                return startDate;
        }
        public void setStartDate(LocalDate startDate) {
            Objects.requireNonNull(startDate, "Start date cannot be null");
            if (this.endDate != null && this.endDate.isBefore(startDate))
                throw new IllegalStateException("End date cannot be before start date");
            this.startDate = startDate;
            saveHolidayShifts();
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
            Objects.requireNonNull(endDate, "End date cannot be null");
            if (this.startDate != null && endDate.isBefore(this.startDate)) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
            this.endDate = endDate;
            saveHolidayShifts();
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
                saveHolidayShifts();
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HolidayShift)) return false;
        if (!super.equals(o)) return false;
        HolidayShift other = (HolidayShift) o;
        return Objects.equals(startDate, other.startDate) && Objects.equals(endDate, other.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDate, endDate);
    }
}

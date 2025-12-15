package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Actors.Employee;
import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Complete: Shift must be either a WeekdayShift or a HolidayShift.
public abstract class Shift implements Extent {
        private static ArrayList<Shift> shifts = new ArrayList<>();
        private static final String FILE_LOCATION = "Shift.ser";

        static {
                loadShiftEvents();
        }

        private BigDecimal bonusPay;
        private LocalTime openTime;
        private LocalTime closeTime;

        // Association Class Links
        private Store store;
        private Employee employee;
        public Shift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, Store store) {
            Validation.validateTime(openTime, closeTime);
            Validation.validateBigDecimal(bonusPay, "Bonus Pay");
            Objects.requireNonNull(store, "Shift must be associated with a Store.");

            this.bonusPay = bonusPay;
            this.openTime = openTime;
            this.closeTime = closeTime;

            this.store = store;
            this.store.addShift(this);

            addShift(this);
        }

        // Association methods, reverse connections
        public Store getStore() {
                return store;
        }

        public void setStore(Store newStore) {
            Objects.requireNonNull(newStore, "Store cannot be null");
            if (this.store != newStore) {
                if (this.store != null) {
                    this.store.removeShift(this);
                }
                this.store = newStore;
                this.store.addShift(this);
                saveShiftEvents();
            }
        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee newEmployee) {
            if (this.employee != newEmployee) {
                if (this.employee != null) {
                    this.employee.removeShift(this);
                }
                this.employee = newEmployee;
                if (newEmployee != null) {
                    this.employee.addShift(this);
                }
                saveShiftEvents();
            }
        }

        // getters and setters
        private static void addShift(Shift shift) {
            if (!shifts.contains(shift))
                shifts.add(shift);
            saveShiftEvents();
        }

        public BigDecimal getBonusPay() {
                return bonusPay;
        }
        public void setBonusPay(BigDecimal bonusPay) {
                Validation.validateBigDecimal(bonusPay, "Bonus Pay");
                this.bonusPay = bonusPay;
                saveShiftEvents();
        }
        public LocalTime getOpenTime() {
                return openTime;
        }
        public void setOpenTime(LocalTime openTime) {
            Objects.requireNonNull(openTime, "Open time cannot be null");
            if(this.closeTime != null && closeTime.isBefore(openTime))
                throw new IllegalArgumentException("Open time cannot be after close time");
            this.openTime = openTime;
            saveShiftEvents();
        }
        public LocalTime getCloseTime() {
                return closeTime;
        }
        public void setCloseTime(LocalTime closeTime) {
            Objects.requireNonNull(closeTime, "Open time cannot be null");
            if(this.openTime != null && closeTime.isAfter(openTime))
                throw new IllegalArgumentException("Open time cannot be after close time");
            this.closeTime = closeTime;
            saveShiftEvents();
        }

        // extend methods
        public static void loadShiftEvents() {
            shifts = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveShiftEvents() {
            Extent.saveClassList(FILE_LOCATION, shifts);
        }

        public static List<Shift> getShiftEvents() {
            return Extent.getImmutableClassList(shifts);
        }

        public void delete() {
            if (this.store != null) {
                this.store.removeShift(this);
                this.store = null;
            }
            if (this.employee != null) {
                this.employee.removeShift(this);
                this.employee = null;
            }
            shifts.remove(this);
            saveShiftEvents();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Shift)) return false;
            Shift shift = (Shift) o;
            return Objects.equals(bonusPay, shift.bonusPay) &&
                    Objects.equals(openTime, shift.openTime) &&
                    Objects.equals(closeTime, shift.closeTime) &&
                    Objects.equals(store, shift.store) &&
                    Objects.equals(employee, shift.employee);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bonusPay, openTime, closeTime, store, employee);
        }
}

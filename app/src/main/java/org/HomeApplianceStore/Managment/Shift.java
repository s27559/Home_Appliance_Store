package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shift implements Extent {
        private static ArrayList<Shift> shifts = new ArrayList<Shift>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Shift.ser";

        private BigDecimal bonusPay;
        private LocalTime openTime;
        private LocalTime closeTime;
        private ArrayList<Store> stores;
        public Shift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime, ArrayList<Store> stores) {
                Validation.validateTime(openTime, closeTime);
                Validation.validateBigDecimal(bonusPay, "Bonus Pay");
                this.bonusPay = bonusPay;
                this.openTime = openTime;
                this.closeTime = closeTime;
                this.stores = stores;
                addShift(this);
        }

        private static void addShift(Shift shift) {
            if (!shifts.contains(shift))
                shifts.add(shift);
            saveShiftEvents();
        }

        public ArrayList<Store> getStores() {
            return stores;
        }
        public void setStores(ArrayList<Store> stores) {
            this.stores = stores;
        }
        public BigDecimal getBonusPay() {
                return bonusPay;
        }
        public void setBonusPay(BigDecimal bonusPay) {
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
            List<Shift> loaded = Extent.loadClassList(FILE_LOCATION);
            shifts = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
        }

        public static void saveShiftEvents() {
            Extent.saveClassList(FILE_LOCATION, shifts);
        }

        public static List<Shift> getShiftEvents() {
            return Extent.getImmutableClassList(shifts);
        }

        public void delete() {
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
                    Objects.equals(stores, shift.stores);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bonusPay, openTime, closeTime, stores);
        }
}

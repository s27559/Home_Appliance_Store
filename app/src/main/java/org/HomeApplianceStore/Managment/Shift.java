package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Shift implements Extent {
        private static ArrayList<Shift> shifts = new ArrayList<Shift>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Shift.ser";

        private BigDecimal bonusPay;
        private LocalTime openTime;
        private LocalTime closeTime;

        public Shift(BigDecimal bonusPay, LocalTime openTime, LocalTime closeTime) {
                this.bonusPay = bonusPay;
                this.openTime = openTime;
                this.closeTime = closeTime;
                addShift(this);
        }

        private static void addShift(Shift shift) {
                shifts.add(shift);
        }

        public BigDecimal getBonusPay() {
                return bonusPay;
        }
        public void setBonusPay(BigDecimal bonusPay) {
                this.bonusPay = bonusPay;
        }
        public LocalTime getOpenTime() {
                return openTime;
        }
        public void setOpenTime(LocalTime openTime) {
                this.openTime = openTime;
        }
        public LocalTime getCloseTime() {
                return closeTime;
        }
        public void setCloseTime(LocalTime closeTime) {
                this.closeTime = closeTime;
        }
}

package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Shift {

        private BigDecimal bonusPay;
        private LocalTime openTime;
        private LocalTime closeTime;
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

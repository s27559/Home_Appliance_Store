package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;

public class Employee {

        private BigDecimal bonusPay;
        private long sickDays;
        private long paidLeaveDays;
        private long unpaidLeaveDays;
        // leaveDays
        // fullPay

        public BigDecimal getBonusPay() {
                return bonusPay;
        }
        public void setBonusPay(BigDecimal bonusPay) {
                this.bonusPay = bonusPay;
        }
        public long getSickDays() {
                return sickDays;
        }
        public void setSickDays(long sickDays) {
                this.sickDays = sickDays;
        }
        public long getPaidLeaveDays() {
                return paidLeaveDays;
        }
        public void setPaidLeaveDays(long paidLeaveDays) {
                this.paidLeaveDays = paidLeaveDays;
        }
        public long getUnpaidLeaveDays() {
                return unpaidLeaveDays;
        }
        public void setUnpaidLeaveDays(long unpaidLeaveDays) {
                this.unpaidLeaveDays = unpaidLeaveDays;
        }
}

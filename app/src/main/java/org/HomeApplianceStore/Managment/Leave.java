package org.HomeApplianceStore.Managment;

import java.time.LocalDate;

public class Leave {

        private boolean isSick;
        private boolean isPaid;
        private LocalDate startDate;
        private LocalDate endDate;
        // periodDays
        public boolean isSick() {
                return isSick;
        }
        public void setSick(boolean isSick) {
                this.isSick = isSick;
        }
        public boolean isPaid() {
                return isPaid;
        }
        public void setPaid(boolean isPaid) {
                this.isPaid = isPaid;
        }
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
}

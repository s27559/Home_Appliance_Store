package org.HomeApplianceStore.Managment;

import java.time.LocalDate;

public class ClosedFor {

        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        // periodDays;

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
        public String getReason() {
                return reason;
        }
        public void setReason(String reason) {
                this.reason = reason;
        }

        
}

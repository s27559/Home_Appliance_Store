package org.HomeApplianceStore.Managment;

import java.time.LocalDate;

public class HolidayShift extends Shift {

        private LocalDate startDate;
        private LocalDate endDate;
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
}

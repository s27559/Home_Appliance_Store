package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contract {

        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal pay;
        // periodDays

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
        public BigDecimal getPay() {
                return pay;
        }
        public void setPay(BigDecimal pay) {
                this.pay = pay;
        }
}

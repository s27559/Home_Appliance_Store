package org.HomeApplianceStore.Products;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Sale {

        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal ammount;
        // periodDays
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
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
        public BigDecimal getAmmount() {
                return ammount;
        }
        public void setAmmount(BigDecimal ammount) {
                this.ammount = ammount;
        }


}

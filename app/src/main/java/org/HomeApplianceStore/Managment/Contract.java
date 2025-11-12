package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.HomeApplianceStore.Actors.Employee;

public class Contract {

        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal pay;
        // periodDays
        private Employee employee;
        private Store store;

        public Employee getEmployee() {
                return employee;
        }
        public void setEmployee(Employee employee) {
                this.employee = employee;
        }
        public Store getStore() {
                return store;
        }
        public void setStore(Store store) {
                this.store = store;
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
        public BigDecimal getPay() {
                return pay;
        }
        public void setPay(BigDecimal pay) {
                this.pay = pay;
        }
}

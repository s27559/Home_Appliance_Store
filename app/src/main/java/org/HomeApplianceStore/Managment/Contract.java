package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.HomeApplianceStore.Actors.Employee;
import org.HomeApplianceStore.Extent;

public class Contract implements Extent {
        private static ArrayList<Contract> contracts = new ArrayList<>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Contract.ser";

        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal pay;
        // periodDays
        private Employee employee;
        private Store store;

        public Contract(LocalDate startDate, LocalDate endDate, BigDecimal pay, Employee employee, Store store) {
            Objects.requireNonNull(employee, "Employee cannot be null");
            Objects.requireNonNull(store, "Store cannot be null");
            Validation.validateDates(startDate, endDate);
            Validation.validateBigDecimal(pay, "Pay");
            this.startDate = startDate;
            this.endDate = endDate;
            this.pay = pay;
            this.employee = employee;
            this.store = store;
            addContract(this);
            saveContracts();
        }

        public long getPeriodDays() {
                if (startDate == null || endDate == null)
                        throw new IllegalStateException("Start date and end date must be set to compute period days");
                return startDate.until(endDate).getDays();
        }

        private static void addContract(Contract contract) {
            if (!contracts.contains(contract))
                contracts.add(contract);
        }

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
            Objects.requireNonNull(startDate, "Start date cannot be null");
            if(this.endDate != null && this.endDate.isBefore(startDate))
                throw new IllegalArgumentException("Start date cannot be after end date");
            this.startDate = startDate;
            saveContracts();
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
            Objects.requireNonNull(endDate, "End date cannot be null");
            if(this.startDate != null && this.startDate.isAfter(endDate)){
                throw new IllegalArgumentException("End date cannot be before start date");
            }
                this.endDate = endDate;
        }
        public BigDecimal getPay() {
                return pay;
        }
        public void setPay(BigDecimal pay) {
                Validation.validateBigDecimal(pay, "Pay");
                this.pay = pay;
                saveContracts();
        }

        // extend methods
        public static void loadContracts() {
            List<Contract> loaded = Extent.loadClassList(FILE_LOCATION);
            contracts = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
        }

        public static void saveContracts() {
                Extent.saveClassList(FILE_LOCATION, contracts);
        }

        public static List<Contract> getContracts() {
                return Extent.getImmutableClassList(contracts);
        }

        public void delete() {
                contracts.remove(this);
                saveContracts();
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract other = (Contract) o;
        return Objects.equals(startDate, other.startDate)
                && Objects.equals(endDate, other.endDate)
                && Objects.equals(pay, other.pay)
                && Objects.equals(employee, other.employee)
                && Objects.equals(store, other.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, pay, employee, store);
    }
}

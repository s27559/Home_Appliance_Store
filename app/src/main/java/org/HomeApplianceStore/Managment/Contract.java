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
        private static final String FILE_LOCATION = "Contract.ser";

        static {
                loadContracts();
        }

        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal pay;

        // Associations
        private Employee employee;
        private  Store store;

        public Contract(LocalDate startDate, LocalDate endDate, BigDecimal pay, Employee employee, Store store) {
            Objects.requireNonNull(employee, "Employee cannot be null");
            Objects.requireNonNull(store, "Store cannot be null");
            Validation.validateDates(startDate, endDate);
            Validation.validateBigDecimal(pay, "Pay");
            this.startDate = startDate;
            this.endDate = endDate;
            this.pay = pay;
            this.employee = employee;
            this.employee.addContract(this);
            this.store = store;
            this.store.addContract(this);

            addContract(this);
            saveContracts();
        }

        // Association Setters
        public void setStore(Store newStore) {
            Objects.requireNonNull(newStore);
            if (this.store != newStore) {
                if (this.store != null) this.store.removeContract(this);
                this.store = newStore;
                this.store.addContract(this);
                saveContracts();
            }
        }

        public Store getStore() { return store; }

        public void setEmployee(Employee newEmployee) {
            Objects.requireNonNull(newEmployee);
            if (this.employee != newEmployee) {
                if (this.employee != null) this.employee.removeContract(this);
                this.employee = newEmployee;
                this.employee.addContract(this);
                saveContracts();
            }
        }

        public Employee getEmployee() { return employee; }

        // Getters and Setters
        public long getPeriodDays() {
                return startDate.until(endDate).getDays();
        }

        private static void addContract(Contract contract) {
            if (!contracts.contains(contract))
                contracts.add(contract);
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
                saveContracts();
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
            contracts = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveContracts() {
                Extent.saveClassList(FILE_LOCATION, contracts);
        }

        public static List<Contract> getContracts() {
                return Extent.getImmutableClassList(contracts);
        }

        public void delete() {
            if(this.store != null) {
                Store s = this.store;
                this.store = null;
                s.removeContract(this);
            }
            if(this.employee != null) {
                Employee e = this.employee;
                this.employee = null;
                e.removeContract(this);
            }
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

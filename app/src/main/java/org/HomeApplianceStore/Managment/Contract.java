package org.HomeApplianceStore.Managment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        private Contract(){
                addContract(this);
        }

        private static void addContract(Contract contract) {
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
                contracts.remove(this);
        }
}

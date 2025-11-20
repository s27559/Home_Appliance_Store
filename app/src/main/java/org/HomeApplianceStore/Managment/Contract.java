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

        static {
                loadContracts();
        }

        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal pay;
        // periodDays

        public Contract(LocalDate startDate, LocalDate endDate, BigDecimal pay) {
            Validation.validateDates(startDate, endDate);
            Validation.validateBigDecimal(pay, "Pay");
            this.startDate = startDate;
            this.endDate = endDate;
            this.pay = pay;
            addContract(this);
            saveContracts();
        }

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
                    && Objects.equals(pay, other.pay);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, pay);
        }
}

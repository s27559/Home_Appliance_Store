package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;

public class Employee implements Extent{
        private static ArrayList<Employee> employees = new ArrayList<>();

        private BigDecimal bonusPay;
        private long sickDays;
        private long paidLeaveDays;
        private long unpaidLeaveDays;
        // leaveDays
        // fullPay
        
        private Person person;

        public Employee(BigDecimal bonusPay, long sickDays, long paidLeaveDays, long unpaidLeaveDays) {
                this.bonusPay = bonusPay;
                this.sickDays = sickDays;
                this.paidLeaveDays = paidLeaveDays;
                this.unpaidLeaveDays = unpaidLeaveDays;

                addEmployee(this);
        }

        public static List<Employee> getEmployees() {
                return Extent.getImmutableClassList(employees);
        }

        public static void loadEmployees(){
                employees = Extent.loadClassList("./org/HomeApplianceStore/Actors/Employee.ser");
        }

        public static void saveEmployees(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Employee.ser", employees);
        }

        private static void addEmployee(Employee employee) {
                employees.add(employee);
        }
        
        public Person getPerson() {
                return person;
        }
        public void setPerson(Person person) {
                this.person = person;
        }
        public BigDecimal getBonusPay() {
                return bonusPay;
        }
        public void setBonusPay(BigDecimal bonusPay) {
                this.bonusPay = bonusPay;
        }
        public long getSickDays() {
                return sickDays;
        }
        public void setSickDays(long sickDays) {
                this.sickDays = sickDays;
        }
        public long getPaidLeaveDays() {
                return paidLeaveDays;
        }
        public void setPaidLeaveDays(long paidLeaveDays) {
                this.paidLeaveDays = paidLeaveDays;
        }
        public long getUnpaidLeaveDays() {
                return unpaidLeaveDays;
        }
        public void setUnpaidLeaveDays(long unpaidLeaveDays) {
                this.unpaidLeaveDays = unpaidLeaveDays;
        }

        public BigDecimal getFullPay(){
                return new BigDecimal(0);
        }

        public long getLeaveDays(){
                return paidLeaveDays + unpaidLeaveDays;
        }
}

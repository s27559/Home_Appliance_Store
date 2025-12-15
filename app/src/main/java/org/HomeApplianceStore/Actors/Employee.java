package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Managment.Contract;
import org.HomeApplianceStore.Managment.Leave;
import org.HomeApplianceStore.Managment.Shift;

public class Employee implements Extent {

        private static ArrayList<Employee> employees = new ArrayList<Employee>();

        static {
                loadEmployees();
        }

        private BigDecimal bonusPay;
        private long sickDays;
        private long paidLeaveDays;
        private long unpaidLeaveDays;
        private EmpRole role;

        public Employee(BigDecimal bonusPay,
                        long sickDays,
                        long paidLeaveDays,
                        long unpaidLeaveDays,
                        EmpRole role ) {
                if (bonusPay == null) {
                    throw new IllegalArgumentException("Bonus pay cannot be null");
                }
                if (role == null) {
                    throw new IllegalArgumentException("Role cannot be null");
                }
                this.setBonusPay(bonusPay);
                this.setSickDays(sickDays);
                this.setPaidLeaveDays(paidLeaveDays);
                this.setUnpaidLeaveDays(unpaidLeaveDays);
                this.role = role;
                addEmployee(this);
        }

        public static void addEmployee(Employee employee) {
                if (employee == null) {
                        throw new IllegalArgumentException("Employee cannot be null");
                }
                employees.add(employee);
                saveEmployees();
        }

        public static void loadEmployees() {
                employees = Extent.loadClassList("./org/HomeApplianceStore/Actors/Employee.ser");
        }

        public static void saveEmployees() {
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Employee.ser", employees);
        }

        public static List<Employee> getEmployees() {
                return Extent.getImmutableClassList(employees);
        }

        public BigDecimal getBonusPay() {
                return bonusPay;
        }

        public void setBonusPay(BigDecimal bonusPay) {
                if (bonusPay != null && bonusPay.signum() < 0) {
                        throw new IllegalArgumentException("Bonus pay cannot be negative");
                }
                this.bonusPay = bonusPay;
        }

        public long getSickDays() {
                return sickDays;
        }

        public void setSickDays(long sickDays) {
                if (sickDays < 0) {
                        throw new IllegalArgumentException("Sick days cannot be negative");
                }
                this.sickDays = sickDays;
        }

        public long getPaidLeaveDays() {
                return paidLeaveDays;
        }

        public void setPaidLeaveDays(long paidLeaveDays) {
                if (paidLeaveDays < 0) {
                        throw new IllegalArgumentException("Paid leave days cannot be negative");
                }
                this.paidLeaveDays = paidLeaveDays;
        }

        public long getUnpaidLeaveDays() {
                return unpaidLeaveDays;
        }

        public void setUnpaidLeaveDays(long unpaidLeaveDays) {
                if (unpaidLeaveDays < 0) {
                        throw new IllegalArgumentException("Unpaid leave days cannot be negative");
                }
                this.unpaidLeaveDays = unpaidLeaveDays;
        }

        public EmpRole getRole() {
                return role;
        }

        public void setRole(EmpRole role) {
                this.role = role;
        }

        public BigDecimal getFullPay(){
                return new BigDecimal(0);
        }

        public long getLeaveDays(){
                return paidLeaveDays + unpaidLeaveDays;
        }

    public void addContract(Contract contract) {
    }

    public void removeContract(Contract contract) {
    }

    public void removeLeave(Leave leave) {
    }

    public void addLeave(Leave leave) {
    }

    public void removeShift(Shift shift) {

    }

    public void addShift(Shift shift) {

    }
}

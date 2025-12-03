package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Actors.Employee;
import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Leave implements Extent {
    private static final String FILE_LOCATION = "Leave.ser";
    private static ArrayList<Leave> leaves = new ArrayList<>();

    static {
        loadLeaves();
    }

    private boolean isSick;
    private boolean isPaid;
    private LocalDate startDate;
    private LocalDate endDate;

    // Association
    private Employee employee;
    private Employee manager;

    public Leave(boolean isSick, boolean isPaid, LocalDate startDate, LocalDate endDate, Employee employee) {
        Validation.validateDates(startDate, endDate);
        Objects.requireNonNull(employee, "Employee cannot be null");

        this.startDate = startDate;
        this.endDate = endDate;
        this.isPaid = isPaid;
        this.isSick = isSick;

        // Reverse Connection
        this.employee = employee;
        this.employee.addLeave(this);

        // Manager is not assigned at creation
        this.manager = null;

        addLeave(this);
        saveLeaves();
    }

    public void setEmployee(Employee newEmployee) {
        Objects.requireNonNull(newEmployee);
        if (this.employee != newEmployee) {
            if (this.employee != null) this.employee.removeLeave(this);
            this.employee = newEmployee;
            this.employee.addLeave(this);
            saveLeaves();
        }
    }
    public Employee getManager() { return manager; }

    public void setApprover(Employee newManager) {
        if (this.manager != newManager) {
            this.manager = newManager;
            saveLeaves();
        }
    }
    public long getPeriodDays() {
        return startDate.until(endDate).getDays();
    }

    private static void addLeave(Leave leave) {
        if (!leaves.contains(leave))
            leaves.add(leave);
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean isSick) {
        this.isSick = isSick;
        saveLeaves();
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
        saveLeaves();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        Objects.requireNonNull(startDate, "Start date cannot be null");
        if (this.endDate != null && this.endDate.isBefore(startDate))
            throw new IllegalArgumentException("Start date cannot be after end date");
        this.startDate = startDate;
        saveLeaves();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        Objects.requireNonNull(endDate, "End date cannot be null");
        if (this.startDate != null && endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        this.endDate = endDate;
        saveLeaves();
    }

    public static void loadLeaves() {
        leaves = Extent.loadClassList(FILE_LOCATION);
    }

    public static void saveLeaves() {
        Extent.saveClassList(FILE_LOCATION, leaves);
    }

    public static List<Leave> getLeaves() {
        return Extent.getImmutableClassList(leaves);
    }

    public void delete() {
        if(this.employee != null) {
            this.employee.removeLeave(this);
            this.employee = null;
        }
        this.manager = null;

        leaves.remove(this);
        saveLeaves();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leave)) return false;
        Leave other = (Leave) o;
        return Objects.equals(isSick, other.isSick)
                && Objects.equals(isPaid, other.isPaid)
                && Objects.equals(startDate, other.startDate)
                && Objects.equals(endDate, other.endDate)
                && Objects.equals(employee, other.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSick, isPaid, startDate, endDate, employee);
    }
}
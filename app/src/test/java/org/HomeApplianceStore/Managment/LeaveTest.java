package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Actors.EmpRole;
import org.HomeApplianceStore.Actors.Employee;
import org.HomeApplianceStore.Address;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LeaveTest {

    private Employee createDummyEmployee() {
        Address address = new Address();
        return new Employee(BigDecimal.ONE, 10L, 5L, 3L, EmpRole.CLERK);
    }
    private Employee applicant;
    private Employee approver;
    @BeforeEach
    void setUp() {
        applicant = new Employee(BigDecimal.ONE, 10L, 5L, 3L, EmpRole.CLERK);
        approver = new Employee(BigDecimal.ONE, 10L, 5L, 3L, EmpRole.CLERK);
    }

    @Test
    void constructorAddsToExtentAndSetsAttributes() {
        LocalDate start = LocalDate.of(3000, 1, 1);
        LocalDate end = LocalDate.of(3000, 1, 5);
        Employee applicant = createDummyEmployee();

        Leave leave = new Leave(true, false, start, end, applicant);

        assertTrue(leave.isSick());
        assertFalse(leave.isPaid());
        assertEquals(start, leave.getStartDate());
        assertEquals(end, leave.getEndDate());

        List<Leave> extent = Leave.getLeaves();
        assertTrue(extent.contains(leave));
    }

    @Test
    void periodDaysCalculationUsesDaysBetween() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 10);
        Employee applicant = createDummyEmployee();

        Leave leave = new Leave(false, true, start, end, applicant);

        long expected = ChronoUnit.DAYS.between(start, end);
        assertEquals(expected, leave.getPeriodDays());
    }

    @Test
    void constructorThrowsOnEndBeforeStart() {
        LocalDate start = LocalDate.of(2024, 6, 10);
        LocalDate end = LocalDate.of(2024, 6, 5);
        Employee applicant = createDummyEmployee();

        assertThrows(IllegalArgumentException.class, () -> {
            new Leave(false, false, start, end, applicant);
        });
    }

    @Test
    void constructorThrowsOnNullEmployee() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 5);

        assertThrows(NullPointerException.class, () -> {
            new Leave(false, false, start, end, null);
        });
    }

    @Test
    void persistenceRoundTripLoadsSavedLeave() throws Exception {
        LocalDate start = LocalDate.of(4000, 1, 1);
        LocalDate end = LocalDate.of(4000, 1, 2);
        Employee applicant = createDummyEmployee();

        Leave leave = new Leave(true, true, start, end, applicant);

        // save
        Leave.saveLeaves();

        // clear
        Field f = Leave.class.getDeclaredField("leaves");
        f.setAccessible(true);
        f.set(null, new ArrayList<Leave>());

        // ensure cleared
        assertTrue(Leave.getLeaves().isEmpty());

        // load
        Leave.loadLeaves();
        boolean found = Leave.getLeaves().stream()
                .anyMatch(l -> l.isSick() == leave.isSick()
                        && l.isPaid() == leave.isPaid()
                        && start.equals(l.getStartDate())
                        && end.equals(l.getEndDate()));
        assertTrue(found, "Persisted Leave should be loaded into extent");
    }

    // -- BYT 6 --
    @Test
    void testSetApproverAssociation() {
        Leave leave = new Leave(true, true, LocalDate.now(), LocalDate.now().plusDays(2), applicant);

        assertNull(leave.getManager());

        // 1. Add Association (Approver)
        leave.setApprover(approver);

        // 2. Check Connection
        assertEquals(approver, leave.getManager());
    }

    @Test
    void testChangeApplicantRemovesFromOld() {
        Leave leave = new Leave(true, true, LocalDate.now(), LocalDate.now().plusDays(2), applicant);
        Employee newApplicant = new Employee(BigDecimal.ONE, 10L, 5L, 3L, EmpRole.CLERK);

        leave.setEmployee(newApplicant);

        assertEquals(newApplicant, leave.getManager());
    }
}
package org.HomeApplianceStore.Actors;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Person createSamplePerson() {
        return new Person("John", "Worker",
                LocalDate.now().minusYears(25),
                null);
    }

    @Test
    void creatingValidEmployeeShouldSucceed() {
        Person person = createSamplePerson();

        Employee emp = new Employee(
                person,
                new BigDecimal("1000.00"),
                2L,
                10L,
                0L
        );

        assertEquals(new BigDecimal("1000.00"), emp.getBonusPay());
        assertEquals(2L, emp.getSickDays());
        assertEquals(10L, emp.getPaidLeaveDays());
        assertEquals(0L, emp.getUnpaidLeaveDays());
    }

    @Test
    void bonusPayCanBeNullOptionalAttribute() {
        Person person = createSamplePerson();

        Employee emp = new Employee(
                person,
                null,
                0L,
                0L,
                0L
        );

        assertNull(emp.getBonusPay());
    }

    @Test
    void negativeBonusPayShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new Employee(
                        person,
                        new BigDecimal("-1.00"),
                        0L,
                        0L,
                        0L
                )
        );
    }

    @Test
    void negativeSickDaysShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new Employee(
                        person,
                        null,
                        -1L,
                        0L,
                        0L
                )
        );
    }

    @Test
    void negativePaidLeaveDaysShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new Employee(
                        person,
                        null,
                        0L,
                        -5L,
                        0L
                )
        );
    }

    @Test
    void negativeUnpaidLeaveDaysShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new Employee(
                        person,
                        null,
                        0L,
                        0L,
                        -3L
                )
        );
    }

    @Test
    void nullPersonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Employee(
                        null,
                        null,
                        0L,
                        0L,
                        0L
                )
        );
    }

    @Test
    void extentShouldUpdateAndBeImmutableForEmployee() {
        int sizeBefore = Employee.getEmployees().size();

        Employee emp = new Employee(
                createSamplePerson(),
                null,
                0L,
                0L,
                0L
        );

        int sizeAfter = Employee.getEmployees().size();
        assertEquals(sizeBefore + 1, sizeAfter);

        List<Employee> immutable = Employee.getEmployees();
        assertThrows(UnsupportedOperationException.class, () ->
                immutable.add(emp)
        );
    }
}

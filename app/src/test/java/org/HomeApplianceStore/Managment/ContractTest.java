package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Actors.EmpRole;
import org.HomeApplianceStore.Actors.Employee;

public class ContractTest {
    private Contract createContract(LocalDate start, LocalDate end, BigDecimal pay, Employee emp, Store store) throws Exception {
        Constructor<Contract> constructor = Contract.class.getDeclaredConstructor(LocalDate.class, LocalDate.class, BigDecimal.class, Employee.class, Store.class);
        constructor.setAccessible(true);
        return constructor.newInstance(start, end, pay, emp, store);
    }

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() throws Exception {
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 1);
        BigDecimal pay = new BigDecimal("5000.00");

        // Dummy dependencies
        Employee employee = new Employee(BigDecimal.ZERO, 0, 0, 0, EmpRole.CLERK);
        Store store = new Store(new Address());

        Contract contract = createContract(start, end, pay, employee, store);

        assertEquals(start, contract.getStartDate());
        assertEquals(end, contract.getEndDate());
        assertEquals(pay, contract.getPay());

        List<Contract> extent = Contract.getContracts();
        assertTrue(extent.contains(contract), "New contract should be added to extent");
    }

    @Test
    void testPeriodDaysCalculation() throws Exception {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 11);
        Employee employee = new Employee(BigDecimal.ZERO, 0, 0, 0, EmpRole.CLERK);
        Store store = new Store(new Address());

        Contract contract = createContract(start, end, BigDecimal.ZERO, employee, store);

        long expected = 10L;
        assertEquals(expected, contract.getPeriodDays());
    }

    @Test
    void testExtentPersistenceRoundTrip() throws Exception {
        LocalDate start = LocalDate.of(2024, 2, 1);
        LocalDate end = LocalDate.of(2024, 8, 1);
        BigDecimal uniquePay = new BigDecimal("12345.67"); // Unique identifier for this test
        Employee employee = new Employee(BigDecimal.ZERO, 0, 0, 0, EmpRole.CLERK);
        Store store = new Store(new Address());

        Contract contract = createContract(start, end, uniquePay, employee, store);

        Contract.saveContracts();

        Field f = Contract.class.getDeclaredField("contracts");
        f.setAccessible(true);
        f.set(null, new ArrayList<Contract>());

        // confirm
        assertTrue(Contract.getContracts().isEmpty());

        // load
        Contract.loadContracts();
        boolean found = Contract.getContracts().stream()
                .anyMatch(c -> uniquePay.equals(c.getPay())
                        && start.equals(c.getStartDate()));

        assertTrue(found, "Persisted Contract should be loaded into extent");
    }
}
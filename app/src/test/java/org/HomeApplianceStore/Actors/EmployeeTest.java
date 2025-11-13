package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

        @Test
        void testCorrectness(){
                
                BigDecimal bonusPay = new BigDecimal("0");
                long sickDays = 10;
                long paidLeaveDays = 20;
                long unpaidLeaveDays = 30;
                Employee employee = new Employee(bonusPay, sickDays, paidLeaveDays, unpaidLeaveDays);
                assertTrue(bonusPay.equals(employee.getBonusPay()));
                assertTrue(sickDays == employee.getSickDays());
                assertTrue(paidLeaveDays == employee.getPaidLeaveDays());
                assertTrue(unpaidLeaveDays == employee.getUnpaidLeaveDays());
                assertTrue(Employee.getEmployees().contains(employee));
        }
}

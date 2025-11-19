package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class LeaveTest {

    @Test
    void testLeaveFlags() {
        LocalDate start = LocalDate.of(2024, 6, 1);
        LocalDate end = LocalDate.of(2024, 6, 10);

        Leave leave = new Leave(true, false, start, end);

        assertTrue(Boolean.TRUE.equals(leave.isSick()));
        assertTrue(Boolean.FALSE.equals(leave.isPaid()));
    }

    @Test
    void testSetIsPaid() {
        Leave leave = new Leave(false, false, LocalDate.now(), LocalDate.now());

        leave.setPaid(true);

        assertTrue(Boolean.TRUE.equals(leave.isPaid()));
    }
}
package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void creatingValidOrderShouldSucceed() {
        LocalDate date = LocalDate.now().minusDays(1);

        Order order = new Order(date, true, true);

        assertEquals(date, order.getDate());
        assertTrue(order.isPaidFor());
        assertTrue(order.isReadyForPickUp());
    }

    @Test
    void futureOrderDateShouldThrow() {
        LocalDate future = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Order(future, false, false)
        );
    }

    @Test
    void nullOrderDateShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(null, false, false)
        );
    }
}

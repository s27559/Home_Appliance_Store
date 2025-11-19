package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    void extentShouldUpdateAndPersistForOrders() {
        int sizeBefore = Order.getOrders().size();

        Order order = new Order(LocalDate.now().minusDays(1), false, false);

        int sizeAfterCreate = Order.getOrders().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Order> immutableList = Order.getOrders();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(order));

        Order.saveOrders();
        Order.loadOrders();

        int sizeAfterReload = Order.getOrders().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}

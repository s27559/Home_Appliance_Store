package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void creatingValidOrderWithReadyForPickUpTrueShouldSucceed() {
        LocalDate date = LocalDate.now().minusDays(1);

        Order order = new Order(date, true, true);

        assertEquals(date, order.getDate());
        assertTrue(order.isPaidFor());
        assertEquals(Boolean.TRUE, order.getReadyForPickUp());
    }

    @Test
    void creatingValidOrderWithReadyForPickUpFalseShouldSucceed() {
        LocalDate date = LocalDate.now().minusDays(2);

        Order order = new Order(date, false, false);

        assertEquals(date, order.getDate());
        assertFalse(order.isPaidFor());
        assertEquals(Boolean.FALSE, order.getReadyForPickUp());
    }

    @Test
    void creatingOrderWithNullReadyForPickUpShouldBeAllowed() {
        LocalDate date = LocalDate.now().minusDays(3);

        Order order = new Order(date, false, null);

        assertEquals(date, order.getDate());
        assertFalse(order.isPaidFor());
        assertNull(order.getReadyForPickUp());
    }

    @Test
    void futureOrderDateShouldThrow() {
        LocalDate future = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Order(future, false, null)
        );
    }

    @Test
    void nullOrderDateShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(null, false, null)
        );
    }

    @Test
    void setReadyForPickUpShouldAcceptNull() {
        LocalDate date = LocalDate.now().minusDays(1);
        Order order = new Order(date, false, true);

        order.setReadyForPickUp(null);

        assertNull(order.getReadyForPickUp());
    }

    @Test
    void extentShouldUpdateAndPersistForOrders() {
        int sizeBefore = Order.getOrders().size();

        Order order = new Order(LocalDate.now().minusDays(1), false, null);

        int sizeAfterCreate = Order.getOrders().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Order> immutableList = Order.getOrders();
        assertThrows(UnsupportedOperationException.class, () ->
                immutableList.add(order)
        );

        Order.saveOrders();
        Order.loadOrders();

        int sizeAfterReload = Order.getOrders().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}



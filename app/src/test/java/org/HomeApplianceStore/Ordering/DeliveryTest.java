package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    private Order createOrder() {
        return new Order(LocalDate.now().minusDays(1), false, null);
    }

    private Delivery createDelivery(String tracking) {
        return new Delivery(
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                BigDecimal.ONE,
                false,
                tracking
        );
    }

    @Test
    void creatingValidDeliveryShouldSucceed() {
        LocalDate send = LocalDate.now().minusDays(2);
        LocalDate receive = LocalDate.now().minusDays(1);
        BigDecimal cost = new BigDecimal("20.50");

        Delivery delivery = new Delivery(send, receive, cost, true, "TRK123");

        assertEquals(send, delivery.getSendDate());
        assertEquals(receive, delivery.getReceiveDate());
        assertEquals(cost, delivery.getCost());
        assertTrue(delivery.isReceived());
        assertEquals("TRK123", delivery.getTrackingNumber());
    }

    @Test
    void sendDateInFutureShouldThrow() {
        LocalDate future = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(future, future, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void receiveDateBeforeSendDateShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now().minusDays(2);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void receiveDateInFutureShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void negativeCostShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, new BigDecimal("-1.00"), false, "TRK1")
        );
    }

    @Test
    void nullCostShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, null, false, "TRK1")
        );
    }

    @Test
    void emptyTrackingNumberShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.ONE, false, "   ")
        );
    }

    @Test
    void extentShouldUpdateAndPersistForDeliveries() {
        int sizeBefore = Delivery.getDeliveries().size();

        LocalDate send = LocalDate.now().minusDays(3);
        LocalDate receive = LocalDate.now().minusDays(2);
        Delivery delivery = new Delivery(send, receive, BigDecimal.ONE, false, "TRK-EXTENT");

        int sizeAfterCreate = Delivery.getDeliveries().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Delivery> immutableList = Delivery.getDeliveries();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(delivery));

        Delivery.saveDeliveries();
        Delivery.loadDeliveries();

        int sizeAfterReload = Delivery.getDeliveries().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }

    //ASSOCIATIONS: DELIVERY–ORDER
    @Test
    void addingDeliveryToOrderShouldSetOrderInDelivery() {
        Order order = createOrder();
        Delivery delivery = createDelivery("TRK-A");

        order.addDelivery(delivery);

        assertEquals(order, delivery.getOrder());
        assertTrue(order.getDeliveries().contains(delivery));
    }

    @Test
    void deliveryCannotBelongToTwoOrders() {
        Order first = createOrder();
        Order second = createOrder();
        Delivery delivery = createDelivery("TRK-B");

        first.addDelivery(delivery);

        assertThrows(IllegalArgumentException.class,
                () -> second.addDelivery(delivery));
        assertEquals(first, delivery.getOrder());
    }

    @Test
    void removingDeliveryFromOrderShouldNullOutOrderInDelivery() {
        Order order = createOrder();
        Delivery delivery = createDelivery("TRK-C");
        order.addDelivery(delivery);

        order.removeDelivery(delivery);

        assertNull(delivery.getOrder());
        assertFalse(order.getDeliveries().contains(delivery));
    }
}
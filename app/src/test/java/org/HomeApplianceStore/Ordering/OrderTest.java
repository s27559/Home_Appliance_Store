package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Ordering.Payment.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order createOrder() {
        return new Order(LocalDate.now().minusDays(1), false, null);
    }

    private Delivery createDelivery(String tracking) {
        // adjust constructor if you have deliveryCompany as an extra parameter
        return new Delivery(
                LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1),
                BigDecimal.TEN,
                false,
                tracking
        );
    }

    private ProductStatus createStatus() {
        return new ProductStatus(
                1L,
                0L,
                false,
                false,
                BigDecimal.ZERO
        );
    }

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

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

    @Test
    void addingDeliveryTwiceShouldNotDuplicate() {
        Order order = createOrder();
        Delivery delivery = createDelivery("TRK-1");

        // first association
        order.addDelivery(delivery);
        // second time – should be ignored
        order.addDelivery(delivery);

        List<Delivery> deliveries = order.getDeliveries();
        assertEquals(1, deliveries.size());
        assertTrue(deliveries.contains(delivery));
        assertEquals(order, delivery.getOrder());
    }

    @Test
    void addingDeliveryAlreadyAssignedToOtherOrderShouldThrow() {
        Order order1 = createOrder();
        Order order2 = createOrder();
        Delivery delivery = createDelivery("TRK-2");

        order1.addDelivery(delivery);

        assertThrows(IllegalArgumentException.class,
                () -> order2.addDelivery(delivery));
    }

    @Test
    void removingDeliveryShouldClearAssociationOnBothSides() {
        Order order = createOrder();
        Delivery delivery = createDelivery("TRK-3");
        order.addDelivery(delivery);

        order.removeDelivery(delivery);

        assertFalse(order.getDeliveries().contains(delivery));
        assertNull(delivery.getOrder());
    }

    @Test
    void getDeliveriesShouldReturnDefensiveCopy() {
        Order order = createOrder();
        Delivery delivery = createDelivery("TRK-4");
        order.addDelivery(delivery);

        List<Delivery> deliveries = order.getDeliveries();
        assertThrows(UnsupportedOperationException.class, deliveries::clear);
    }

    //ASSOCIATIONS: ORDER–PRODUCTSTATUS

    @Test
    void addingProductStatusShouldSetOrderAndBeInList() {
        Order order = createOrder();
        ProductStatus status = createStatus();

        order.addProductStatus(status);

        assertEquals(order, status.getOrder());
        assertTrue(order.getProductStatuses().contains(status));
    }

    @Test
    void removingProductStatusShouldClearAssociation() {
        Order order = createOrder();
        ProductStatus status = createStatus();
        order.addProductStatus(status);

        order.removeProductStatus(status);

        assertFalse(order.getProductStatuses().contains(status));
        assertNull(status.getOrder());
    }

    //ASSOCIATIONS: ORDER–DELIVERY (DERIVED ATTRIBUTE)

    @Test
    void orderCostShouldBeSumOfDeliveryCosts() {
        Order order = createOrder();
        Delivery d1 = createDelivery("TRK-5"); // cost = 10
        Delivery d2 = new Delivery(
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                new BigDecimal("5.50"),
                false,
                "TRK-6"
        );

        order.addDelivery(d1);
        order.addDelivery(d2);

        assertEquals(new BigDecimal("15.50"), order.getCost());
    }

    //ASSOCIATIONS: ORDER–PAYMENTMETHOD
    @Test
    void settingPaymentMethodShouldUpdateBothSides() {
        Order order = createOrder();
        PaymentMethod method = createPaymentMethod("Card");

        order.setPaymentMethod(method);

        assertEquals(method, order.getPaymentMethod());
        assertTrue(method.getOrders().contains(order));
    }
}



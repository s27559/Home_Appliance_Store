package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Ordering.Payment.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    //helpers

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

    private Order createOrder(String methodName) {
        PaymentMethod pm = createPaymentMethod(methodName);
        return new Order(LocalDate.now().minusDays(1), false, null, pm);
    }

    private Delivery createDelivery(Order order, String tracking, BigDecimal cost) {
        return new Delivery(
                order,
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                cost,
                false,
                tracking,
                "DHL"
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

    // assosiation ORDER – PAYMENTMETHOD

    @Test
    void constructorShouldSetPaymentMethodAndRegisterOrderInMethod() {
        PaymentMethod method = createPaymentMethod("Card");
        Order order = new Order(LocalDate.now().minusDays(1), false, null, method);

        assertEquals(method, order.getPaymentMethod());
        assertTrue(method.getOrders().contains(order));
    }

    @Test
    void settingNewPaymentMethodShouldMoveOrderBetweenMethods() {
        PaymentMethod pm1 = createPaymentMethod("PM1");
        PaymentMethod pm2 = createPaymentMethod("PM2");
        Order order = new Order(LocalDate.now().minusDays(1), false, null, pm1);

        order.setPaymentMethod(pm2);

        assertEquals(pm2, order.getPaymentMethod());
        assertFalse(pm1.getOrders().contains(order));
        assertTrue(pm2.getOrders().contains(order));
    }

    @Test
    void settingSamePaymentMethodShouldNotDuplicateOrderInMethod() {
        PaymentMethod method = createPaymentMethod("Card");
        Order order = new Order(LocalDate.now().minusDays(1), false, null, method);

        order.setPaymentMethod(method);

        long count = method.getOrders().stream()
                .filter(o -> o == order)
                .count();

        assertEquals(1, count);
    }

    @Test
    void constructorWithNullPaymentMethodShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order(LocalDate.now(), false, null, null));
    }

    // assosiation ORDER – DELIVERY

    @Test
    void deliveriesCreatedWithOrderShouldAppearInOrderAndHaveBackReference() {
        Order order = createOrder("Card");
        Delivery d1 = createDelivery(order, "TRK-1", new BigDecimal("10.00"));
        Delivery d2 = createDelivery(order, "TRK-2", new BigDecimal("5.00"));

        List<Delivery> deliveries = order.getDeliveries();
        assertTrue(deliveries.contains(d1));
        assertTrue(deliveries.contains(d2));
        assertEquals(order, d1.getOrder());
        assertEquals(order, d2.getOrder());
    }

    @Test
    void orderCostShouldBeSumOfDeliveryCosts() {
        Order order = createOrder("Card");
        createDelivery(order, "TRK-3", new BigDecimal("10.00"));
        createDelivery(order, "TRK-4", new BigDecimal("5.50"));

        assertEquals(new BigDecimal("15.50"), order.getCost());
    }

    @Test
    void removingDeliveryShouldUpdateCostAndClearAssociation() {
        Order order = createOrder("Card");
        Delivery d1 = createDelivery(order, "TRK-5", new BigDecimal("10.00"));
        Delivery d2 = createDelivery(order, "TRK-6", new BigDecimal("5.00"));

        assertEquals(new BigDecimal("15.00"), order.getCost());

        order.removeDelivery(d1);

        assertEquals(new BigDecimal("5.00"), order.getCost());
        assertNull(d1.getOrder());
        assertFalse(order.getDeliveries().contains(d1));
    }

    @Test
    void getDeliveriesShouldReturnDefensiveCopy() {
        Order order = createOrder("Card");
        Delivery d1 = createDelivery(order, "TRK-7", new BigDecimal("3.00"));

        List<Delivery> copy = order.getDeliveries();
        copy.clear();

        assertEquals(1, order.getDeliveries().size());
        assertTrue(order.getDeliveries().contains(d1));
    }

    // assosiation ORDER – PRODUCTSTATUS

    @Test
    void addProductStatusShouldSetOrderAndAddToCollection() {
        Order order = createOrder("Card");
        ProductStatus status = createStatus();

        order.addProductStatus(status);

        assertEquals(order, status.getOrder());
        assertTrue(order.getProductStatuses().contains(status));
    }

    @Test
    void addingProductStatusAlreadyAssignedToAnotherOrderShouldThrow() {
        Order first = createOrder("PM1");
        Order second = createOrder("PM2");
        ProductStatus status = createStatus();

        first.addProductStatus(status);

        assertThrows(IllegalArgumentException.class,
                () -> second.addProductStatus(status));
    }

    @Test
    void getProductStatusesShouldReturnDefensiveCopy() {
        Order order = createOrder("Card");
        ProductStatus status = createStatus();
        order.addProductStatus(status);

        List<ProductStatus> copy = order.getProductStatuses();
        copy.clear();

        assertEquals(1, order.getProductStatuses().size());
        assertTrue(order.getProductStatuses().contains(status));
    }
}
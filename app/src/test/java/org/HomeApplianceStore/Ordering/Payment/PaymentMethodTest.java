package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Ordering.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

    private Order createOrderWith(PaymentMethod method) {
        return new Order(LocalDate.now().minusDays(1), false, null, method);
    }

    // association PAYMENTMETHOD – ORDER

    @Test
    void orderConstructorShouldRegisterOrderInPaymentMethodsOrders() {
        PaymentMethod method = createPaymentMethod("Card");

        Order order = createOrderWith(method);

        assertEquals(method, order.getPaymentMethod());
        assertTrue(method.getOrders().contains(order));
    }

    @Test
    void addOrderShouldRegisterOrderAndUpdateOrdersPaymentMethod() {
        PaymentMethod pm1 = createPaymentMethod("PM1");
        PaymentMethod pm2 = createPaymentMethod("PM2");
        Order order = createOrderWith(pm1);

        pm2.addOrder(order);

        assertEquals(pm2, order.getPaymentMethod());
        assertFalse(pm1.getOrders().contains(order));
        assertTrue(pm2.getOrders().contains(order));
    }

    @Test
    void addOrderWithNullShouldThrow() {
        PaymentMethod method = createPaymentMethod("Cash");

        assertThrows(IllegalArgumentException.class, () -> method.addOrder(null));
    }

    @Test
    void addOrderShouldNotDuplicateSameOrder() {
        PaymentMethod method = createPaymentMethod("Card");
        Order order = createOrderWith(method);

        method.addOrder(order);

        long count = method.getOrders().stream()
                .filter(o -> o == order)
                .count();
        assertEquals(1, count);
    }

    @Test
    void removeOrderShouldDetachOrderFromMethodListButKeepOrderPaymentMethodUpdatedByOrderSide() {
        PaymentMethod method = createPaymentMethod("Card");
        Order order = createOrderWith(method);

        method.removeOrder(order);
        assertFalse(method.getOrders().contains(order));
    }

    @Test
    void getOrdersShouldBeImmutable() {
        PaymentMethod method = createPaymentMethod("Card");
        Order order = createOrderWith(method);

        List<Order> orders = method.getOrders();

        assertTrue(orders.contains(order));
        assertThrows(UnsupportedOperationException.class,
                () -> orders.add(createOrderWith(method)));
    }

    @Test
    void changingPaymentMethodViaOrderShouldUpdateCollectionsOnBothMethods() {
        PaymentMethod pm1 = createPaymentMethod("PM1");
        PaymentMethod pm2 = createPaymentMethod("PM2");
        Order order = createOrderWith(pm1);

        order.setPaymentMethod(pm2);

        assertEquals(pm2, order.getPaymentMethod());
        assertFalse(pm1.getOrders().contains(order));
        assertTrue(pm2.getOrders().contains(order));
    }
}
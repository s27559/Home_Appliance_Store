package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Ordering.Payment.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

    private Order createOrder(PaymentMethod method) {
        return new Order(LocalDate.now().minusDays(1), false, null, method);
    }

    private Delivery createDelivery(Order order, String tracking) {
        return new Delivery(
                order,
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                new BigDecimal("12.00"),
                false,
                tracking,
                "FedEx"
        );
    }

    //ASSOCIATION: DELIVERY – ORDER

    @Test
    void deliveryConstructorShouldCreateAssociationWithOrder() {
        PaymentMethod pm = createPaymentMethod("Card");
        Order order = createOrder(pm);

        Delivery delivery = createDelivery(order, "D1");

        assertEquals(order, delivery.getOrder());
        assertTrue(order.getDeliveries().contains(delivery));
    }

    @Test
    void deliveryCannotBelongToTwoDifferentOrders() {
        PaymentMethod pm1 = createPaymentMethod("PM1");
        PaymentMethod pm2 = createPaymentMethod("PM2");
        Order first = createOrder(pm1);
        Order second = createOrder(pm2);

        Delivery delivery = createDelivery(first, "D2");

        assertThrows(IllegalArgumentException.class,
                () -> second.addDelivery(delivery));
        assertEquals(first, delivery.getOrder());
    }

    @Test
    void removingDeliveryFromOrderShouldNullOutOrderReferenceInDelivery() {
        PaymentMethod pm = createPaymentMethod("Card");
        Order order = createOrder(pm);

        Delivery delivery = createDelivery(order, "D3");
        assertEquals(order, delivery.getOrder());

        order.removeDelivery(delivery);

        assertNull(delivery.getOrder());
        assertFalse(order.getDeliveries().contains(delivery));
    }
}
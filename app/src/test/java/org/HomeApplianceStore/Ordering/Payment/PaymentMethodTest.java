package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Ordering.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
    private Order createOrder() {
        return new Order(LocalDate.now().minusDays(1), false, null);
    }

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

    @Test
    void validNameShouldCreatePaymentMethod() {
        PaymentMethod method = new PaymentMethod("Cash");
        assertEquals("Cash", method.getName());
    }

    @Test
    void nullNameShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new PaymentMethod(null)
        );
    }

    @Test
    void emptyNameShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new PaymentMethod("   ")
        );
    }

    @Test
    void extentShouldUpdateAndPersistForPaymentMethod() {
        int sizeBefore = PaymentMethod.getMethods().size();

        PaymentMethod method = new PaymentMethod("Bank Transfer");

        int sizeAfterCreate = PaymentMethod.getMethods().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<PaymentMethod> immutableList = PaymentMethod.getMethods();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(method));

        PaymentMethod.saveMethods();
        PaymentMethod.loadMethods();

        int sizeAfterReload = PaymentMethod.getMethods().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }

    //ASSOCIATIONS: PAYMENTMETHOD–ORDER
    @Test
    void changingPaymentMethodShouldMoveOrderBetweenMethods() {
        Order order = createOrder();
        PaymentMethod m1 = createPaymentMethod("Card");
        PaymentMethod m2 = createPaymentMethod("Paypal");

        order.setPaymentMethod(m1);
        order.setPaymentMethod(m2);

        assertFalse(m1.getOrders().contains(order));
        assertTrue(m2.getOrders().contains(order));
        assertEquals(m2, order.getPaymentMethod());
    }

    @Test
    void getOrdersShouldBeImmutable() {
        PaymentMethod method = createPaymentMethod("Cash");
        Order order = createOrder();
        order.setPaymentMethod(method);

        List<Order> orders = method.getOrders();
        assertThrows(UnsupportedOperationException.class, () -> orders.add(createOrder()));
    }

    @Test
    void addOrderWithNullShouldThrow() {
        PaymentMethod method = createPaymentMethod("Blik");
        assertThrows(IllegalArgumentException.class, () -> method.addOrder(null));
    }
}

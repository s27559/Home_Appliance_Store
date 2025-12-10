package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Ordering.Payment.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {

    private PaymentMethod createPaymentMethod(String name) {
        return new PaymentMethod(name);
    }

    private Order createOrder(String methodName) {
        PaymentMethod pm = createPaymentMethod(methodName);
        return new Order(LocalDate.now().minusDays(1), false, null, pm);
    }

    private ProductStatus createStatus() {
        return new ProductStatus(
                2L,
                1L,
                false,
                true,
                BigDecimal.ZERO
        );
    }

    // asosiation PRODUCTSTATUS – ORDER

    @Test
    void productStatusAssignedToOrderThroughAddProductStatus() {
        Order order = createOrder("Card");
        ProductStatus status = createStatus();

        order.addProductStatus(status);

        assertEquals(order, status.getOrder());
        assertTrue(order.getProductStatuses().contains(status));
    }

    @Test
    void addingSameProductStatusTwiceShouldNotDuplicate() {
        Order order = createOrder("Card");
        ProductStatus status = createStatus();

        order.addProductStatus(status);
        order.addProductStatus(status);

        assertEquals(1, order.getProductStatuses().size());
    }

    @Test
    void productStatusCanBeMovedToAnotherOrderAfterRemoval() {
        Order first = createOrder("PM1");
        Order second = createOrder("PM2");
        ProductStatus status = createStatus();

        first.addProductStatus(status);
        first.removeProductStatus(status);
        second.addProductStatus(status);

        assertEquals(second, status.getOrder());
        assertFalse(first.getProductStatuses().contains(status));
        assertTrue(second.getProductStatuses().contains(status));
    }

    @Test
    void removingProductStatusShouldClearAssociationOnBothSides() {
        Order order = createOrder("Card");
        ProductStatus status = createStatus();
        order.addProductStatus(status);

        order.removeProductStatus(status);

        assertNull(status.getOrder());
        assertFalse(order.getProductStatuses().contains(status));
    }

    @Test
    void productStatusInitiallyHasNoOrder() {
        ProductStatus status = createStatus();

        assertNull(status.getOrder());
    }
}
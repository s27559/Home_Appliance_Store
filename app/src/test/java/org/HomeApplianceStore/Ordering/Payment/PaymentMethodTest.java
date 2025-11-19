package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

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
}

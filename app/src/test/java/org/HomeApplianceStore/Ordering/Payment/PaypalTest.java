package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaypalTest {

    @Test
    void creatingValidPaypalShouldSucceed() {
        Paypal paypal = new Paypal("PayPal", "user@example.com");

        assertEquals("user@example.com", paypal.getPaypalAccountId());
        assertEquals("PayPal", paypal.getName());
    }

    @Test
    void emptyAccountIdShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Paypal("PayPal", "   ")
        );
    }

    @Test
    void accountIdWithoutAtShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Paypal("PayPal", "userexample.com")
        );
    }

    @Test
    void nullAccountIdShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Paypal("PayPal", null)
        );
    }
}

package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void extentShouldUpdateAndPersistForPaypal() {
        int sizeBefore = Paypal.getPaypals().size();

        Paypal paypal = new Paypal("PayPal", "extent@example.com");

        int sizeAfterCreate = Paypal.getPaypals().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Paypal> immutableList = Paypal.getPaypals();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(paypal));

        Paypal.savePaypals();
        Paypal.loadPaypals();

        int sizeAfterReload = Paypal.getPaypals().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}
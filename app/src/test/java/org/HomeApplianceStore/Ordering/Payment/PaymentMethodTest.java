package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}

package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlikTest {

    @Test
    void creatingValidBlikShouldSucceed() {
        Blik blik = new Blik("Blik", "123456", "TX-1");

        assertEquals("123456", blik.getCode());
        assertEquals("TX-1", blik.getTransactionId());
    }

    @Test
    void emptyCodeShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blik("Blik", "   ", "TX-1")
        );
    }

    @Test
    void codeWithNonDigitsShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blik("Blik", "12A456", "TX-1")
        );
    }

    @Test
    void codeWithWrongLengthShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blik("Blik", "12345", "TX-1")
        );
    }

    @Test
    void emptyTransactionIdShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Blik("Blik", "123456", "   ")
        );
    }

    @Test
    void extentShouldUpdateAndPersistForBlik() {
        int sizeBefore = Blik.getBliks().size();

        Blik blik = new Blik("Blik", "654321", "TX-EXTENT");

        int sizeAfterCreate = Blik.getBliks().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Blik> immutableList = Blik.getBliks();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(blik));

        Blik.saveBliks();
        Blik.loadBliks();

        int sizeAfterReload = Blik.getBliks().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}
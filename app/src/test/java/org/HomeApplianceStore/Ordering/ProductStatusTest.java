package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {

    @Test
    void creatingValidProductStatusShouldSucceed() {
        ProductStatus status = new ProductStatus(
                10L,
                5L,
                true,
                false,
                new BigDecimal("15.00")
        );

        assertEquals(10L, status.getAmmountNew());
        assertEquals(5L, status.getAmmountUsed());
        assertTrue(status.isToBeMoved());
        assertFalse(status.isToBeIntegrated());
        assertEquals(new BigDecimal("15.00"), status.getDifferenceInPrice());
    }

    @Test
    void negativeNewAmountShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new ProductStatus(-1L, 0L, false, false, BigDecimal.ZERO)
        );
    }

    @Test
    void negativeUsedAmountShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new ProductStatus(0L, -1L, false, false, BigDecimal.ZERO)
        );
    }

    @Test
    void nullDifferenceInPriceShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new ProductStatus(1L, 1L, false, false, null)
        );
    }

    @Test
    void extentShouldUpdateAndPersistForProductStatus() {
        int sizeBefore = ProductStatus.getStatuses().size();

        ProductStatus status = new ProductStatus(
                1L,
                0L,
                false,
                false,
                BigDecimal.ZERO
        );

        int sizeAfterCreate = ProductStatus.getStatuses().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<ProductStatus> immutableList = ProductStatus.getStatuses();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(status));

        ProductStatus.saveStatuses();
        ProductStatus.loadStatuses();

        int sizeAfterReload = ProductStatus.getStatuses().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}

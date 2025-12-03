package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {

    private Order createOrder() {
        return new Order(LocalDate.now().minusDays(1), false, null);
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

    //ASSOCIATIONS: PRODUCTSTATUS–ORDER
    @Test
    void productStatusAssignedToOrderThroughOrderAdd() {
        Order order = createOrder();
        ProductStatus status = createStatus();

        order.addProductStatus(status);

        assertEquals(order, status.getOrder());
        assertTrue(order.getProductStatuses().contains(status));
    }

    @Test
    void productStatusCanBeMovedToAnotherOrderAfterRemoval() {
        Order first = createOrder();
        Order second = createOrder();
        ProductStatus status = createStatus();

        first.addProductStatus(status);
        first.removeProductStatus(status);
        second.addProductStatus(status);

        assertEquals(second, status.getOrder());
        assertFalse(first.getProductStatuses().contains(status));
        assertTrue(second.getProductStatuses().contains(status));
    }
}
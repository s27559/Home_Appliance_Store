package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FreestandingProductTest {

    private static final BigDecimal VALID_COST = new BigDecimal("50.556");
    private static final BigDecimal EXPECTED_SCALED_COST = new BigDecimal("50.56");
    private static final BigDecimal ZERO_COST = BigDecimal.ZERO;

    @Test
    void constructor_works_andScalesMoveCost_whenValid() {
        FreestandingProduct product = new FreestandingProduct(VALID_COST);

        assertEquals(EXPECTED_SCALED_COST, product.getMoveCost());
        assertEquals(2, product.getMoveCost().scale());
    }

    @Test
    void constructor_works_whenCostIsZero() {
        FreestandingProduct product = new FreestandingProduct(ZERO_COST);

        assertEquals(BigDecimal.ZERO.setScale(2), product.getMoveCost());
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenMoveCostIsNegative() {
        BigDecimal negativeCost = new BigDecimal("-0.01");

        assertThrows(IllegalArgumentException.class, () -> {
            new FreestandingProduct(negativeCost);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenMoveCostIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FreestandingProduct(null);
        });
    }

    @Test
    void setMoveCost_works_andScalesValue_whenValid() {
        FreestandingProduct product = new FreestandingProduct(VALID_COST);
        BigDecimal newCost = new BigDecimal("10.123");
        BigDecimal expectedNewCost = new BigDecimal("10.12");

        product.setMoveCost(newCost);

        assertEquals(expectedNewCost, product.getMoveCost());
        assertEquals(2, product.getMoveCost().scale());
    }

    @Test
    void setMoveCost_throwsNullPointerException_whenMoveCostIsNull() {
        FreestandingProduct product = new FreestandingProduct(VALID_COST);

        assertThrows(NullPointerException.class, () -> product.setMoveCost(null));

        assertEquals(EXPECTED_SCALED_COST, product.getMoveCost());
    }

    @Test
    void setMoveCost_throwsIllegalArgumentException_whenMoveCostIsNegative() {
        FreestandingProduct product = new FreestandingProduct(VALID_COST);
        BigDecimal negativeCost = new BigDecimal("-5.00");

        assertThrows(IllegalArgumentException.class, () -> product.setMoveCost(negativeCost));

        assertEquals(EXPECTED_SCALED_COST, product.getMoveCost());
    }

    @Test
    void addFreestandingProduct_preventsDuplicatesBasedOnEquals_identityLogic() {
        FreestandingProduct p1 = new FreestandingProduct(new BigDecimal("10.00"));
        FreestandingProduct p2 = new FreestandingProduct(new BigDecimal("10.00"));
        FreestandingProduct p3 = new FreestandingProduct(new BigDecimal("20.00"));

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnMoveCost() {
        FreestandingProduct p1 = new FreestandingProduct(new BigDecimal("50.50"));
        FreestandingProduct p2 = new FreestandingProduct(new BigDecimal("50.50"));
        FreestandingProduct p3 = new FreestandingProduct(new BigDecimal("50.51"));

        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());

        assertFalse(p1.equals(p3));
    }
}

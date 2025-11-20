package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class IntegratedProductTest {

    private static final String VALID_NAME = "Integrated Fridge";
    private static final BigDecimal VALID_COST = new BigDecimal("49.995");
    private static final BigDecimal EXPECTED_SCALED_COST = new BigDecimal("50.00");
    private static final boolean VALID_MUST_BE_DONE = true;

    @BeforeEach
    void setup() {
    }

    @Test
    void constructor_works_andInitializesFields_whenValid() {
        IntegratedProduct product = new IntegratedProduct(VALID_NAME, VALID_COST, VALID_MUST_BE_DONE);

        assertEquals(EXPECTED_SCALED_COST, product.getIntegrationCost());
        assertTrue(product.isMustBeDone());
        assertEquals(2, product.getIntegrationCost().scale());
    }

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new IntegratedProduct(null, VALID_COST, VALID_MUST_BE_DONE);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenCostIsNegative() {
        BigDecimal negativeCost = new BigDecimal("-0.01");
        assertThrows(IllegalArgumentException.class, () -> {
            new IntegratedProduct(VALID_NAME, negativeCost, VALID_MUST_BE_DONE);
        });
    }

    @Test
    void constructor_throwsNullPointerException_whenCostIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new IntegratedProduct(VALID_NAME, null, VALID_MUST_BE_DONE);
        });
    }

    @Test
    void setIntegrationCost_works_andScalesValue_whenValid() {
        IntegratedProduct product = new IntegratedProduct(VALID_NAME, VALID_COST, VALID_MUST_BE_DONE);
        BigDecimal newCost = new BigDecimal("10.123");
        BigDecimal expectedNewCost = new BigDecimal("10.12");

        product.setIntegrationCost(newCost);

        assertEquals(expectedNewCost, product.getIntegrationCost());
        assertEquals(2, product.getIntegrationCost().scale());
    }

    @Test
    void setIntegrationCost_throwsNullPointerException_whenCostIsNull() {
        IntegratedProduct product = new IntegratedProduct(VALID_NAME, VALID_COST, VALID_MUST_BE_DONE);

        assertThrows(NullPointerException.class, () -> product.setIntegrationCost(null));

        assertEquals(EXPECTED_SCALED_COST, product.getIntegrationCost());
    }

    @Test
    void setIntegrationCost_throwsIllegalArgumentException_whenCostIsNegative() {
        IntegratedProduct product = new IntegratedProduct(VALID_NAME, VALID_COST, VALID_MUST_BE_DONE);
        BigDecimal negativeCost = new BigDecimal("-5.00");

        assertThrows(IllegalArgumentException.class, () -> product.setIntegrationCost(negativeCost));

        assertEquals(EXPECTED_SCALED_COST, product.getIntegrationCost());
    }

    @Test
    void setMustBeDone_works_andUpdatesValue() {
        IntegratedProduct product = new IntegratedProduct(VALID_NAME, VALID_COST, false);

        product.setMustBeDone(true);
        assertTrue(product.isMustBeDone());

        product.setMustBeDone(false);
        assertFalse(product.isMustBeDone());
    }

    @Test
    void addIntegratedProduct_preventsDuplicatesBasedOnEquals_identityLogic() {
        IntegratedProduct p1 = new IntegratedProduct(VALID_NAME, new BigDecimal("10.00"), true);
        IntegratedProduct p2 = new IntegratedProduct(VALID_NAME, new BigDecimal("10.00"), true);
        IntegratedProduct p3 = new IntegratedProduct(VALID_NAME, new BigDecimal("20.00"), false);

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnCostAndMustBeDone() {
        IntegratedProduct p1 = new IntegratedProduct(VALID_NAME, new BigDecimal("50.50"), true);
        IntegratedProduct p2 = new IntegratedProduct("Other", new BigDecimal("50.50"), true);
        IntegratedProduct p3 = new IntegratedProduct(VALID_NAME, new BigDecimal("50.51"), true);
        IntegratedProduct p4 = new IntegratedProduct(VALID_NAME, new BigDecimal("50.50"), false);

        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());

        assertFalse(p1.equals(p3));

        assertFalse(p1.equals(p4));
    }
}
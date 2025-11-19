package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private static final BigDecimal VALID_PRICE = new BigDecimal("100.00");
    private static final BigDecimal VALID_WEIGHT = new BigDecimal("5.50");
    private static final long VALID_WARRANTY = 365;
    private static final String VALID_NAME = "Dishwasher X";
    private static final String VALID_MODEL = "DW-3000";

    private static ArrayList<Property> mockProperties;

    @BeforeAll
    static void init() {
        mockProperties = new ArrayList<>();
        mockProperties.add(new Property("Voltage", "220V"));
    }

    @BeforeEach
    void resetMinPrice() {
        try {
            Product.setMinPrice(BigDecimal.ZERO);
        } catch (Exception e) {
        }
    }

    // --- Constructor & Field Initialization Tests ---

    @Test
    void constructor_works_andInitializesFields_whenValid() {
        // Constructor call must now match the 9-parameter order: name, desc, modelNumber, newPrice, usedPrice, weight, brand, properties, warrantyDays
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);

        assertEquals(VALID_NAME, p.getName());
        assertEquals(VALID_MODEL, p.getModelNumber());
        assertEquals(VALID_WARRANTY, p.getWarrantyDays());

        assertEquals(new BigDecimal("100.00"), p.getNewPrice());
        assertEquals(2, p.getNewPrice().scale());
    }

    // --- Constructor Validation Tests ---

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Product(null, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(" ", "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        });
    }

    @Test
    void constructor_throwsNullPointerException_whenModelNumberIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Product(VALID_NAME, "Desc", null, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenNewPriceIsNegative() {
        BigDecimal negativePrice = new BigDecimal("-1.00");
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, negativePrice, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenWeightIsZero() {
        BigDecimal zeroWeight = BigDecimal.ZERO;
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, zeroWeight, "Brand", mockProperties, VALID_WARRANTY);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenWarrantyDaysIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, -1L);
        });
    }

    // --- Static Setter Validation Test ---

    @Test
    void setMinPrice_throwsIllegalArgumentException_whenNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product.setMinPrice(new BigDecimal("-0.01"));
        });
    }

    @Test
    void setMinPrice_works_whenNonNegative() {
        BigDecimal newMinPrice = new BigDecimal("10.50");
        Product.setMinPrice(newMinPrice);
        assertEquals(newMinPrice, Product.getMinPrice());
    }

    // --- Individual Setter Validation Tests ---

    @Test
    void setNewPrice_throwsException_whenNegative() {
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        assertThrows(IllegalArgumentException.class, () -> p.setNewPrice(new BigDecimal("-0.01")));
        assertEquals(VALID_PRICE, p.getNewPrice());
    }

    @Test
    void setWeight_throwsException_whenZero() {
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        assertThrows(IllegalArgumentException.class, () -> p.setWeight(BigDecimal.ZERO));
        assertEquals(VALID_WEIGHT, p.getWeight());
    }

    @Test
    void setWarrantyDays_throwsException_whenNegative() {
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        assertThrows(IllegalArgumentException.class, () -> p.setWarrantyDays(-10L));
        assertEquals(VALID_WARRANTY, p.getWarrantyDays());
    }

    @Test
    void setName_throwsException_whenBlank() {
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        assertThrows(IllegalArgumentException.class, () -> p.setName(""));
        assertEquals(VALID_NAME, p.getName());
    }

    @Test
    void setModelNumber_throwsException_whenNull() {
        Product p = new Product(VALID_NAME, "Desc", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "Brand", mockProperties, VALID_WARRANTY);
        assertThrows(NullPointerException.class, () -> p.setModelNumber(null));
        assertEquals(VALID_MODEL, p.getModelNumber());
    }

    // --- Identity and Equality Tests ---

    @Test
    void equalsAndHashCode_shouldBeBasedOnModelNumber() {
        Product p1 = new Product(VALID_NAME, "Desc1", VALID_MODEL, VALID_PRICE, VALID_PRICE, VALID_WEIGHT, "BrandA", mockProperties, VALID_WARRANTY);
        Product p2 = new Product("Different Name", "Desc2", VALID_MODEL, VALID_PRICE.add(BigDecimal.ONE), VALID_PRICE, VALID_WEIGHT, "BrandB", new ArrayList<>(), 1L);

        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());

        p2.setModelNumber("DW-4000");
        assertFalse(p1.equals(p2));
    }
}
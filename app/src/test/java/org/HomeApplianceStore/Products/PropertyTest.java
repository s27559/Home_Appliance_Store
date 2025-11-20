package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    private static final String VALID_TYPE = "Material";
    private static final String VALID_VALUE_STRING = "Steel";
    private static final Integer VALID_VALUE_INT = 100;

    @Test
    void constructor_works_andInitializesFields_StringValue() {
        Property<String> property = new Property<>(VALID_TYPE, VALID_VALUE_STRING);

        assertEquals(VALID_TYPE, property.getTypeName());
        assertEquals(VALID_VALUE_STRING, property.getValue());
    }

    @Test
    void constructor_works_andInitializesFields_IntegerValue() {
        Property<Integer> property = new Property<>(VALID_TYPE, VALID_VALUE_INT);

        assertEquals(VALID_TYPE, property.getTypeName());
        assertEquals(VALID_VALUE_INT, property.getValue());
    }

    @Test
    void constructor_throwsNullPointerException_whenTypeNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Property<>(null, VALID_VALUE_STRING);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenTypeNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Property<>(" ", VALID_VALUE_STRING);
        });
    }

    @Test
    void constructor_throwsNullPointerException_whenValueIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Property<>(VALID_TYPE, null);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenValueIsStringEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Property<>(VALID_TYPE, "");
        });
    }

    @Test
    void setTypeName_works_andUpdatesValue_whenValid() {
        Property<String> property = new Property<>(VALID_TYPE, VALID_VALUE_STRING);
        String newType = "Finish";

        property.setTypeName(newType);

        assertEquals(newType, property.getTypeName());
    }

    @Test
    void setTypeName_throwsIllegalArgumentException_whenTypeNameIsEmpty() {
        Property<String> property = new Property<>(VALID_TYPE, VALID_VALUE_STRING);

        assertThrows(IllegalArgumentException.class, () -> property.setTypeName(""));

        assertEquals(VALID_TYPE, property.getTypeName());
    }

    @Test
    void setValue_works_andUpdatesValue_whenValid() {
        Property<Integer> property = new Property<>(VALID_TYPE, VALID_VALUE_INT);
        Integer newValue = 200;

        property.setValue(newValue);

        assertEquals(newValue, property.getValue());
    }

    @Test
    void setValue_throwsNullPointerException_whenValueIsNull() {
        Property<String> property = new Property<>(VALID_TYPE, VALID_VALUE_STRING);

        assertThrows(NullPointerException.class, () -> property.setValue(null));

        assertEquals(VALID_VALUE_STRING, property.getValue());
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnTypeNameAndValue() {
        Property<String> p1 = new Property<>(VALID_TYPE, "A");
        Property<String> p2 = new Property<>(VALID_TYPE, "A");
        Property<String> p3 = new Property<>(VALID_TYPE, "B");
        Property<String> p4 = new Property<>("Size", "A");

        assertTrue(p1.equals(p2));
        assertEquals(p1.hashCode(), p2.hashCode());

        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
    }
}
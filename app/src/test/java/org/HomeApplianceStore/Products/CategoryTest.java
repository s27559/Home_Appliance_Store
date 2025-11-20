package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private static ArrayList<Property> mockProperties;

    @BeforeAll
    static void init() {
        mockProperties = new ArrayList<>();
        mockProperties.add(new Property("Color", "White"));
    }

    @Test
    void constructor_works_andInitializesFields_whenValid() {
        String expectedName = "Appliances";
        Category category = new Category(expectedName, mockProperties);

        assertEquals(expectedName, category.getName());
        assertEquals(mockProperties, category.getProperties());
    }

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new Category(null, mockProperties);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenNameIsWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category("  ", mockProperties);
        });
    }

    @Test
    void setName_works_andUpdatesName_whenValid() {
        Category category = new Category("Old Name", null);
        String newName = "New Name";

        category.setName(newName);

        assertEquals(newName, category.getName());
    }

    @Test
    void setName_throwsIllegalArgumentException_whenNameIsEmpty() {
        Category category = new Category("Initial", null);

        assertThrows(IllegalArgumentException.class, () -> category.setName(""));

        assertEquals("Initial", category.getName());
    }

    @Test
    void setProperties_works_andUpdatesList_whenValid() {
        Category category = new Category("Test", null);
        ArrayList<Property> newProps = new ArrayList<>();
        newProps.add(new Property("Type", "Gas"));

        category.setProperties(newProps);

        assertTrue(Objects.equals(newProps, category.getProperties()));
    }

    @Test
    void setProperties_works_andAcceptsNull() {
        Category category = new Category("Test", mockProperties);

        category.setProperties(null);

        assertNull(category.getProperties());
    }

    @Test
    void getProperties_returnsUnmodifiableList() {
        Category category = new Category("Test", mockProperties);

        List<Property> immutableList = category.getProperties();

        assertThrows(UnsupportedOperationException.class, () -> {
            immutableList.add(new Property("Fail", "Value"));
        });
    }

    @Test
    void addCategory_preventsDuplicatesBasedOnEquals_identityLogic() {
        Category c1 = new Category("Duplicate", null);
        Category c2 = new Category("Duplicate", mockProperties);
        Category c3 = new Category("Unique", null);

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnName() {
        Category c1 = new Category("Kitchen", null);
        Category c2 = new Category("Kitchen", mockProperties);
        Category c3 = new Category("Bathroom", null);

        assertTrue(c1.equals(c2));
        assertEquals(c1.hashCode(), c2.hashCode());

        assertFalse(c1.equals(c3));
    }
}
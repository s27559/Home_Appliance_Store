package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Property<T extends Serializable> implements Extent{

    private static ArrayList<Property> properties = new ArrayList<Property>();

    private String typeName;
    private T value;

    public Property(String typeName, T value) {
        validateTypeName(typeName);
        validateValue(value);

        this.typeName = typeName;
        this.value = value;

        addProperty(this);
        saveProperties();
    }
    private void validateTypeName(String typeName) {
        Objects.requireNonNull(typeName, "Property type name cannot be null.");
        if (typeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Property type name cannot be empty.");
        }
    }
    private void validateValue(T value) {
        Objects.requireNonNull(value, "Property value cannot be null.");
        if (value.equals("")) {
            throw new IllegalArgumentException("Property value cannot be empty.");
        }
    }

    private static void addProperty(Property property) {
        if(!properties.contains(property)) {
            properties.add(property);
        }
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        validateTypeName(typeName);
        this.typeName = typeName;
        saveProperties();
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        validateValue(value);
        this.value = value;
        saveProperties();
    }

    public static void LoadProperties() {
        properties = Extent.loadClassList("./org/HomeApplianceStore/Products/Property.ser");
    }

    public static void saveProperties() {
        Extent.saveClassList("./org/HomeApplianceStore/Products/Property.ser", properties);
    }

    public static List<Property> getProperties() {
        return Extent.getImmutableClassList(properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return Objects.equals(getTypeName(), property.getTypeName()) &&
                Objects.equals(getValue(), property.getValue());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTypeName(), getValue());
    }
}
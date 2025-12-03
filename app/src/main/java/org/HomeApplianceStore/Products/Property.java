package org.HomeApplianceStore.Products;

import java.io.Serializable;
import java.util.Objects;

public class Property<T extends Serializable> implements Serializable{
    private String typeName;
    private T value;

    public Property(String typeName, T value) {
        validateTypeName(typeName);
        if (value!=null) {
            validateValue(value);
        }
        validateValue(value);

        this.typeName = typeName;
        this.value = value;
    }
    private void validateTypeName(String typeName) {
        Objects.requireNonNull(typeName, "Property type name cannot be null.");
        if (typeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Property type name cannot be empty.");
        }
    }
    private void validateValue(T value) {
        if (value.equals("")) {
            throw new IllegalArgumentException("Property value cannot be empty.");
        }
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        validateTypeName(typeName);
        this.typeName = typeName;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        validateValue(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property<?> property)) return false;
        return Objects.equals(getTypeName(), property.getTypeName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTypeName());
    }
}

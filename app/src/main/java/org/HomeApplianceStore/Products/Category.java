package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.*;

public class Category implements Extent {

    private Category parentCategory;
    private Set<Category> subCategories = new HashSet<>();

    private static ArrayList<Category> categories = new ArrayList<>();

        static {
                loadCategories();
        }

    private String name;
    private Set<Property<?>> requiredProperties = new HashSet<>();

    public Category(String name, ArrayList<Property<?>> initialProperties) {
        Objects.requireNonNull(name, "Category name cannot be null");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (initialProperties != null) {
            for (Property<?> property : initialProperties) {
                if (property.getValue() != null) {
                    throw new IllegalArgumentException("Category properties must be templates (value must be null)");
                }
            }
            this.requiredProperties.addAll(initialProperties);
        }
        this.name = name;

        addCategory(this);
        Category.saveCategories();
    }

    private static void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        this.name = name;
        Category.saveCategories();
    }
    public Set<Property<?>> getRequiredProperties() {
        return Collections.unmodifiableSet(requiredProperties);
    }
    public void setProperties(Set<Property<?>> newProperties) {
        if (newProperties != null) {
            for  (Property<?> property : newProperties) {
                if (property.getValue() != null) {
                    throw new IllegalArgumentException("Category properties must be templates (value must be null)");
                }
            }
            this.requiredProperties = newProperties;
        } else {
            this.requiredProperties = new HashSet<>();
        }
        Category.saveCategories();
    }
    public void addRequiredProperty(Property<?> property) {
        Objects.requireNonNull(property, "Property cannot be null.");
        if (property.getValue() != null) {
            throw new IllegalArgumentException("Category property must be a template (value must be null).");
        }
        this.requiredProperties.add(property);
        Category.saveCategories();
    }
    public void removeRequiredProperty(Property<?> property) {
        Objects.requireNonNull(property, "Property cannot be null.");
        this.requiredProperties.remove(property);
        Category.saveCategories();
    }
    public static void loadCategories(){
        if(categories.isEmpty()){
            categories = Extent.loadClassList("./org/HomeApplianceStore/Products/Category.ser");
        }
    }
    public static void saveCategories(){
        Extent.saveClassList("./org/HomeApplianceStore/Products/Category.ser", categories);
    }

    public Category getParentCategory() {
        return parentCategory;
    }
    public Set<Category> getSubCategories() {
        return Collections.unmodifiableSet(this.subCategories);
    }

    public static List<Category> getCategories() {
        return Extent.getImmutableClassList(categories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category other)) return false;

        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

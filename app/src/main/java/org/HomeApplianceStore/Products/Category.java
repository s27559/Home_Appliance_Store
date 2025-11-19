package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Category implements Extent {

        private static ArrayList<Category> categories = new ArrayList<>();

        private String name;
        private ArrayList<Property> properties;

        public Category(String name, ArrayList<Property> properties) {
            Objects.requireNonNull(name, "Category name cannot be null");
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Category name cannot be null or empty");
            }
            this.name = name;
            this.properties = properties;

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
        public List<Property> getProperties() {
                return (properties == null) ? null : Collections.unmodifiableList(properties);
        }
        public void setProperties(ArrayList<Property> properties) {
                this.properties = properties;
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

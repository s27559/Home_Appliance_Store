package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Category implements Extent {

        private static ArrayList<Category> categories = new ArrayList<Category>();

        private String name;
        private ArrayList<Property> properties;

        public Category(String name, ArrayList<Property> properties) {
            this.name = name;
            this.properties = properties;

            addCategory(this);
        }

        private static void addCategory(Category category) {
            categories.add(category);
        }

        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public ArrayList<Property> getProperties() {
                return properties;
        }
        public void setProperties(ArrayList<Property> properties) {
                this.properties = properties;
        }

        public static void loadCategories(){
            categories = Extent.loadClassList("./org/HomeApplianceStore/Products/Category.ser");
        }

        public static void saveCategories(){
            Extent.saveClassList("./org/HomeApplianceStore/Products/Category.ser", categories);
        }

        public static List<Category> getCategories() {
            return Extent.getImmutableClassList(categories);
        }
}

package org.HomeApplianceStore.Products;

import java.util.ArrayList;

public class Category {

        private static ArrayList<Category> categories = new ArrayList<Category>();

        private String name;
        private ArrayList<Property> properties;
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
}

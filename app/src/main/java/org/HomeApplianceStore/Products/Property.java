package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Property {

        private static ArrayList<Property> properties = new ArrayList<Property>();

        private String typeName;
        private String value;

        public Property(String typeName, String value) {
            this.typeName = typeName;
            this.value = value;

            addProperty(this);
        }

        private static void addProperty(Property property) {
            properties.add(property);
        }

        public String getTypeName() {
                return typeName;
        }
        public void setTypeName(String typeName) {
                this.typeName = typeName;
        }
        public String getValue() {
                return value;
        }
        public void setValue(String value) {
                this.value = value;
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
}

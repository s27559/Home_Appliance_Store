package org.HomeApplianceStore.Products;

import java.util.ArrayList;

public class Property {

        private static ArrayList<Property> properties = new ArrayList<Property>();

        private String typeName;
        private String value;

        public Property(String typeName, String value) {
            this.typeName = typeName;
            this.value = value;

            properties.add(this);
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
}

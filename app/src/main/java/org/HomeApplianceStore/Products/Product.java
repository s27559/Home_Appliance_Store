package org.HomeApplianceStore.Products;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Product {

        private static BigDecimal minPrice;
        private String name;
        private String desc;
        private String modelNumber;
        private BigDecimal newPrice;
        private BigDecimal usedPrice;
        private BigDecimal weight;
        private long worrantyDays;
        private String brand;
        private ArrayList<Property> properties;
        public static BigDecimal getMinPrice() {
                return minPrice;
        }
        public static void setMinPrice(BigDecimal minPrice) {
                Product.minPrice = minPrice;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getDesc() {
                return desc;
        }
        public void setDesc(String desc) {
                this.desc = desc;
        }
        public String getModelNumber() {
                return modelNumber;
        }
        public void setModelNumber(String modelNumber) {
                this.modelNumber = modelNumber;
        }
        public BigDecimal getNewPrice() {
                return newPrice;
        }
        public void setNewPrice(BigDecimal newPrice) {
                this.newPrice = newPrice;
        }
        public BigDecimal getUsedPrice() {
                return usedPrice;
        }
        public void setUsedPrice(BigDecimal usedPrice) {
                this.usedPrice = usedPrice;
        }
        public BigDecimal getWeight() {
                return weight;
        }
        public void setWeight(BigDecimal weight) {
                this.weight = weight;
        }
        public long getWorrantyDays() {
                return worrantyDays;
        }
        public void setWorrantyDays(long worrantyDays) {
                this.worrantyDays = worrantyDays;
        }
        public String getBrand() {
                return brand;
        }
        public void setBrand(String brand) {
                this.brand = brand;
        }
        public ArrayList<Property> getProperties() {
                return properties;
        }
        public void setProperties(ArrayList<Property> properties) {
                this.properties = properties;
        }


}

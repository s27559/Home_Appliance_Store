package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Extent{

        private static ArrayList<Product> products = new ArrayList<Product>();

        private static BigDecimal minPrice;
        private String name;
        private String desc;
        private String modelNumber;
        private BigDecimal newPrice;
        private BigDecimal usedPrice;
        private BigDecimal weight;
        private long warrantyDays;
        private String brand;
        private List<Property> properties;

        public Product(String name, String desc, String modelNumber, BigDecimal newPrice, BigDecimal usedPrice,  BigDecimal weight, String brand, ArrayList<Property> properties, long warrantyDays) {
            Objects.requireNonNull(name, "Product name cannot null");
            if(name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot empty");
            }
            Objects.requireNonNull(modelNumber, "Product model number cannot null");
            validatePrice(newPrice);
            validatePrice(usedPrice);
            validateWeight(weight);
            validateWarranty(warrantyDays);

            this.name = name;
            this.desc = desc;
            this.modelNumber = modelNumber;
            this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.warrantyDays = warrantyDays;
            this.brand = brand;
            this.properties = properties;

            addProduct(this);
            saveProducts();
        }
        private void validatePrice(BigDecimal price) {
            if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Price must be non-negative.");
            }
        }
        private void validateWeight(BigDecimal weight) {
            if (weight != null && weight.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Weight must be positive.");
            }
        }
        private void validateWarranty(long days) {
            if (days < 0) {
                throw new IllegalArgumentException("Warranty days must be non-negative.");
            }
        }


        private static void addProduct(Product product) {
            if(!products.contains(product)){
                products.add(product);
            }
        }

        public static BigDecimal getMinPrice() {
                return minPrice;
        }
        public static void setMinPrice(BigDecimal minPrice) {
                if(minPrice.compareTo(BigDecimal.ZERO) < 0){
                    throw new IllegalArgumentException("Minimum price must be non-negative.");
                }
                Product.minPrice = minPrice;
                saveProducts();
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                Objects.requireNonNull(name, "Product name cannot null");
                if(name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Product name cannot empty");
                }
                this.name = name;
                saveProducts();
        }
        public String getDesc() {
                return desc;
        }
        public void setDesc(String desc) {
                this.desc = desc;
                saveProducts();
        }
        public String getModelNumber() {
                return modelNumber;
        }
        public void setModelNumber(String modelNumber) {
                Objects.requireNonNull(modelNumber, "Product model number cannot null");
                this.modelNumber = modelNumber;
                saveProducts();
        }
        public BigDecimal getNewPrice() {
                return newPrice;
        }
        public void setNewPrice(BigDecimal newPrice) {
                validatePrice(newPrice);
                this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                saveProducts();
        }
        public BigDecimal getUsedPrice() {
                return usedPrice;
        }
        public void setUsedPrice(BigDecimal usedPrice) {
                validatePrice(usedPrice);
                this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                saveProducts();
        }
        public BigDecimal getWeight() {
                return weight;
        }
        public void setWeight(BigDecimal weight) {
                validateWeight(weight);
                this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
                saveProducts();
        }
        public long getWarrantyDays() {
                return warrantyDays;
        }
        public void setWarrantyDays(long warrantyDays) {
                validateWarranty(warrantyDays);
                this.warrantyDays = warrantyDays;
                saveProducts();
        }
        public String getBrand() {
                return brand;
        }
        public void setBrand(String brand) {
                this.brand = brand;
                saveProducts();
        }
        public List<Property> getProperties() {
                return properties;
        }
        public void setProperties(List<Property> properties) {
                this.properties = properties;
                saveProducts();
        }

        public static void LoadProducts() {
            products = Extent.loadClassList("./org/HomeApplianceStore/Products/Product.ser");
        }

        public static void saveProducts() {
            Extent.saveClassList("./org/HomeApplianceStore/Products/Product.ser", products);
        }

        public static List<Product> getProducts() {
            return Extent.getImmutableClassList(products);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return Objects.equals(modelNumber, product.modelNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(modelNumber);
        }
}

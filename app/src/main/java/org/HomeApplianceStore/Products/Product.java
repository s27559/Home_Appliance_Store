package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Actors.CartProduct;
import org.HomeApplianceStore.Actors.Customer;

import java.math.BigDecimal;
import java.util.*;

public class Product implements Extent{

    private static ArrayList<Product> products = new ArrayList<Product>();

    static {
        LoadProducts();
    }

    private Category category;
    private Set<Property<?>> properties = new HashSet<>();
    private Set<Storage> storageRecords = new HashSet<>();
    private Set<Sale> sales = new HashSet<>();

    private static BigDecimal minPrice;
    private String name;
    private String desc;
    private String modelNumber;
    private BigDecimal newPrice;
    private BigDecimal usedPrice;
    private BigDecimal weight;
    private long warrantyDays;
    private String brand;

    private FreestandingImpl freestandingProduct;
    private Set<IntegratedImpl>  integratedProducts = new HashSet<>();

    //constructor for a product that is freestanding 0...1
    //move cost is a parameter of class freestanding product
    Product(BigDecimal moveCost, String name, String desc, String modelNumber, BigDecimal newPrice, BigDecimal usedPrice,  BigDecimal weight, String brand, Set<Property<?>> initialProperties, long warrantyDays, Category category) {
        Objects.requireNonNull(name, "Product name cannot null");
        if(name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot empty");
        }
        Objects.requireNonNull(modelNumber, "Product model number cannot null");
        validatePrice(newPrice);
        validatePrice(usedPrice);
        validateWeight(weight);
        validateWarranty(warrantyDays);
        Objects.requireNonNull(category, "Product category cannot null");
        this.category = category;

        if (initialProperties == null) {
            initialProperties = new HashSet<>();
        }
        validatePropertiesAgainstCategory(initialProperties, category);
        this.properties.addAll(initialProperties);

        this.name = name;
        this.desc = desc;
        this.modelNumber = modelNumber;
        this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.warrantyDays = warrantyDays;
        this.brand = brand;

        this.sales = new  HashSet<>();

        this.freestandingProduct = new FreestandingImpl(moveCost);

        addProduct(this);
        saveProducts();
    }

    //constructor for an integrated product 0...*
    //IntegratedConfig is a configuration for an integrated product
    Product(Set<IntegratedConfig> integrationOptions, String name, String desc, String modelNumber, BigDecimal newPrice, BigDecimal usedPrice,  BigDecimal weight, String brand, Set<Property<?>> initialProperties, long warrantyDays, Category category) {
        Objects.requireNonNull(name, "Product name cannot null");
        if(name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot empty");
        }
        Objects.requireNonNull(modelNumber, "Product model number cannot null");
        validatePrice(newPrice);
        validatePrice(usedPrice);
        validateWeight(weight);
        validateWarranty(warrantyDays);
        Objects.requireNonNull(category, "Product category cannot null");
        this.category = category;

        if (initialProperties == null) {
            initialProperties = new HashSet<>();
        }
        validatePropertiesAgainstCategory(initialProperties, category);
        this.properties.addAll(initialProperties);

        this.name = name;
        this.desc = desc;
        this.modelNumber = modelNumber;
        this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.warrantyDays = warrantyDays;
        this.brand = brand;

        this.sales = new  HashSet<>();

        for (IntegratedConfig config : integrationOptions) {
            this.integratedProducts.add(new IntegratedImpl(config.name, config.cost, config.mustBeDone));
        }

        addProduct(this);
        saveProducts();
    }

    //for a both freestanding and integrated product
    Product(BigDecimal moveCost, List<IntegratedConfig> integrationOptions, String name, String desc, String modelNumber, BigDecimal newPrice, BigDecimal usedPrice,  BigDecimal weight, String brand, Set<Property<?>> initialProperties, long warrantyDays, Category category) {
        Objects.requireNonNull(name, "Product name cannot null");
        if(name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot empty");
        }
        Objects.requireNonNull(modelNumber, "Product model number cannot null");
        validatePrice(newPrice);
        validatePrice(usedPrice);
        validateWeight(weight);
        validateWarranty(warrantyDays);
        Objects.requireNonNull(category, "Product category cannot null");
        this.category = category;

        if (initialProperties == null) {
            initialProperties = new HashSet<>();
        }
        validatePropertiesAgainstCategory(initialProperties, category);
        this.properties.addAll(initialProperties);

        this.name = name;
        this.desc = desc;
        this.modelNumber = modelNumber;
        this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.warrantyDays = warrantyDays;
        this.brand = brand;

        this.sales = new  HashSet<>();

        this.freestandingProduct = new FreestandingImpl(moveCost);
        for (IntegratedConfig config : integrationOptions) {
            this.integratedProducts.add(new IntegratedImpl(config.name, config.cost, config.mustBeDone));
        }

        addProduct(this);
        saveProducts();
    }

    //constructor for a product that is not freestanding or integrated
    Product(String name, String desc, String modelNumber, BigDecimal newPrice, BigDecimal usedPrice,  BigDecimal weight, String brand, Set<Property<?>> initialProperties, long warrantyDays, Category category) {
        Objects.requireNonNull(name, "Product name cannot null");
        if(name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot empty");
        }
        Objects.requireNonNull(modelNumber, "Product model number cannot null");
        validatePrice(newPrice);
        validatePrice(usedPrice);
        validateWeight(weight);
        validateWarranty(warrantyDays);
        Objects.requireNonNull(category, "Product category cannot null");
        this.category = category;

        if (initialProperties == null) {
            initialProperties = new HashSet<>();
        }
        validatePropertiesAgainstCategory(initialProperties, category);
        this.properties.addAll(initialProperties);

        this.name = name;
        this.desc = desc;
        this.modelNumber = modelNumber;
        this.newPrice = newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.usedPrice = usedPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.weight = weight.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.warrantyDays = warrantyDays;
        this.brand = brand;

        this.sales = new  HashSet<>();

        addProduct(this);
        saveProducts();
    }

    //inner classes have no Extent since they saved automatically when Product is saved

    private class FreestandingImpl {
        private BigDecimal moveCost;

        public FreestandingImpl(BigDecimal moveCost) {
            setMoveCost(moveCost);
        }

        public BigDecimal getMoveCost() {
            return moveCost;
        }

        public void setMoveCost(BigDecimal moveCost) {
            if (moveCost == null || moveCost.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Move cost cannot be negative");
            }
            this.moveCost = moveCost.setScale(2, BigDecimal.ROUND_HALF_UP);
            Product.saveProducts();
        }
    }

    private class IntegratedImpl {
        private String integrationName;
        private BigDecimal integrationCost;
        private boolean mustBeDone;

        public IntegratedImpl(String name, BigDecimal integrationCost, boolean mustBeDone) {
            this.integrationName = name;
            this.mustBeDone = mustBeDone;
            setIntegrationCost(integrationCost);
        }

        public BigDecimal getIntegrationCost() {
            return integrationCost;
        }

        public void setIntegrationCost(BigDecimal integrationCost) {
            if (integrationCost == null || integrationCost.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Integration cost cannot be negative");
            }
            this.integrationCost = integrationCost.setScale(2, BigDecimal.ROUND_HALF_UP);
            Product.saveProducts();
        }

        public boolean isMustBeDone() { return mustBeDone; }
        public void setMustBeDone(boolean val) {
            this.mustBeDone = val;
            Product.saveProducts();
        }
    }
    //helper class to pass to the constructor
    public static class IntegratedConfig {
        String name;
        BigDecimal cost;
        boolean mustBeDone;
        public IntegratedConfig(String name, BigDecimal cost, boolean mustBeDone) {
            this.name = name;
            this.cost = cost;
            this.mustBeDone = mustBeDone;
        }
    }
    public boolean isFreestanding() {
        return freestandingProduct != null;
    }

    public BigDecimal getMoveCost() {
        if (!isFreestanding()) throw new IllegalStateException("Product is not freestanding");
        return freestandingProduct.getMoveCost();
    }

    public void setMoveCost(BigDecimal cost) {
        if (!isFreestanding()) {
            this.freestandingProduct = new FreestandingImpl(cost);
        } else {
            this.freestandingProduct.setMoveCost(cost);
        }
    }

    private void validatePropertiesAgainstCategory(Set<Property<?>> productProperties, Category category) {

        Set<Property<?>> requiredTypes = category.getRequiredProperties();

        for (Property<?> requiredTemplate : requiredTypes) {
            boolean typeFound = false;

            for (Property<?> productProp : productProperties) {
                if (productProp.getTypeName().equals(requiredTemplate.getTypeName())) {
                    typeFound = true;

                    if (productProp.getValue() == null) {
                        throw new IllegalArgumentException("Property '" + productProp.getTypeName() + "' must have a value set, as required by Category.");
                    }
                    break;
                }
            }

            if (!typeFound) {
                throw new IllegalArgumentException("Product is missing the required property type from Category: " + requiredTemplate.getTypeName());
            }
        }

        for (Property<?> productProp : productProperties) {
            if (!requiredTypes.contains(productProp)) {
                throw new IllegalArgumentException("Product contains unsupported property type: " + productProp.getTypeName());
            }
        }
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

    public Set<Storage> getStorageRecords() {
        return Collections.unmodifiableSet(storageRecords);
    }

    public void addStorage(Storage storage) {
        Objects.requireNonNull(storage, "Storage record cannot be null.");
        storageRecords.add(storage);
        saveProducts();
    }

    public Category getCategory() {
        return category;
    }

    public Set<Property<?>> getProperties() {
        return Collections.unmodifiableSet(properties);
    }
    public FreestandingImpl getFreestandingProduct() {
        return freestandingProduct;
    }

    public void setFreestandingProduct(FreestandingImpl freestandingProduct) {
        if (Objects.equals(this.freestandingProduct, freestandingProduct)) {
            return;
        }

        this.freestandingProduct = freestandingProduct;

        saveProducts();
    }

    public Set<IntegratedImpl> getIntegratedProducts() {
        return Collections.unmodifiableSet(integratedProducts);
    }
    public void addProperty(Property<?> property) {
        Objects.requireNonNull(property, "Property cannot be null.");

        Set<Property<?>> tempProps = new HashSet<>(this.properties);
        tempProps.add(property);

        validatePropertiesAgainstCategory(tempProps, this.category);

        this.properties.add(property);
        saveProducts();
    }

    public void removeProperty(Property<?> property) {
        Objects.requireNonNull(property, "Property cannot be null.");

        Set<Property<?>> tempProps = new HashSet<>(this.properties);
        tempProps.remove(property);

        validatePropertiesAgainstCategory(tempProps, this.category);

        this.properties.remove(property);
        saveProducts();
    }

    public void addIntegratedProduct(IntegratedImpl integratedProduct) {
        Objects.requireNonNull(integratedProduct, "Integrated product cannot be null.");

        if (integratedProducts.contains(integratedProduct)) {
            return;
        }

        integratedProducts.add(integratedProduct);

        saveProducts();
    }

    public Set<Sale> getSales() {
        return Collections.unmodifiableSet(sales);
    }

    public void addSale(Sale sale) {
        Objects.requireNonNull(sale, "Sale to add cannot be null.");

        if (sales.contains(sale)) {
            return;
        }

        sales.add(sale);

        sale.addProduct(this);
        saveProducts();
    }

    public void removeSale(Sale sale) {
        Objects.requireNonNull(sale, "Sale to remove cannot be null.");

        if (sales.remove(sale)) {
            sale.removeProduct(this);
            saveProducts();
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

        public void addCartProduct(long amountNew, long amountUsed, Customer customer){
                new CartProduct(amountNew, amountUsed, customer, this);
        }

        public void removeCartProduct(Customer customer){
                for(CartProduct cartProduct : customer.getCartProducts()){
                        if(cartProduct.getProduct() == this) customer.removeCartProduct(cartProduct);
                }
        }
}

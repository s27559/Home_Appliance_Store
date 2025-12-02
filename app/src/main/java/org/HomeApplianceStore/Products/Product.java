package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

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

    private static BigDecimal minPrice;
    private String name;
    private String desc;
    private String modelNumber;
    private BigDecimal newPrice;
    private BigDecimal usedPrice;
    private BigDecimal weight;
    private long warrantyDays;
    private String brand;

    private Set<Sale> sales = new HashSet<Sale>();
    private FreestandingProduct freestandingProduct;
    private Set<IntegratedProduct>  integratedProducts = new HashSet<>();

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
        this.integratedProducts = new HashSet<>();
        this.freestandingProduct = null;

        addProduct(this);
        saveProducts();
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
    void removeStorageReverse(Storage storage) {
        Objects.requireNonNull(storage, "Storage record cannot be null.");
        storageRecords.remove(storage);
        saveProducts();
    }
    public Category getCategory() {
        return category;
    }

    public Set<Property<?>> getProperties() {
        return Collections.unmodifiableSet(properties);
    }
    public FreestandingProduct getFreestandingProduct() {
        return freestandingProduct;
    }
    void setFreestandingProduct(FreestandingProduct freestandingProduct) {
        this.freestandingProduct = freestandingProduct;
        saveProducts();
    }
    public Set<IntegratedProduct> getIntegratedProducts() {
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
    void addIntegratedProduct(IntegratedProduct integratedProduct) {
        Objects.requireNonNull(integratedProduct, "Integrated product cannot be null.");
        integratedProducts.add(integratedProduct);
        saveProducts();
    }
    void removeIntegratedPart(IntegratedProduct integratedProduct) {
        Objects.requireNonNull(integratedProduct, "Integrated product cannot be null.");
        integratedProducts.remove(integratedProduct);
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
        sale.addProductReverse(this);
        saveProducts();
    }

    public void removeSale(Sale sale) {
        Objects.requireNonNull(sale, "Sale to remove cannot be null.");

        if (sales.remove(sale)) {
            sale.removeProductReverse(this);
            saveProducts();
        }
    }
    void addSaleReverse(Sale sale) {
        sales.add(sale);
        saveProducts();
    }

    void removeSaleReverse(Sale sale) {
        sales.remove(sale);
        saveProducts();
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
}

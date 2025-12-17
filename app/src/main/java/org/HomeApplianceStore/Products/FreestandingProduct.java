package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreestandingProduct implements Extent{

    private static ArrayList<FreestandingProduct> freestandingProducts =new ArrayList<FreestandingProduct>();

    static {
        loadFreestandingProducts();
    }

    private Product product;
    private BigDecimal moveCost;

    public FreestandingProduct(Product product, BigDecimal moveCost) {
        validateProduct(product);
        if (moveCost == null || moveCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Move cost cannot be negative");
        }

        setProduct(product);

        this.moveCost = moveCost.setScale(2, BigDecimal.ROUND_HALF_UP);

        addFreestandingProduct(this);
        saveFreestandingProducts();
    }

    public void setProduct(Product newProduct) {
        Objects.requireNonNull(newProduct, "FreestandingProduct requires a Product.");

        if (Objects.equals(this.product, newProduct)) {
            return;
        }

        if (newProduct.getFreestandingProduct() != null && !newProduct.getFreestandingProduct().equals(this)) {
            throw new IllegalStateException("Product is already associated with another FreestandingProduct.");
        }

        if (this.product != null) {
            this.product.setFreestandingProduct(null);
        }

        this.product = newProduct;

        this.product.setFreestandingProduct(this);
    }

    public void removeProduct(Product productToRemove) {
        if (!Objects.equals(this.product, productToRemove)) {
            return;
        }

        this.product.setFreestandingProduct(null);
        this.product = null;
    }

    private void validateProduct(Product product) {
        Objects.requireNonNull(product, "FreestandingProduct must be associated with a Product (1).");
    }
    private static void addFreestandingProduct(FreestandingProduct freestandingProduct) {
        if(!freestandingProducts.contains(freestandingProduct))
            freestandingProducts.add(freestandingProduct);
    }
    public Product getProduct() {
        return product;
    }
    public BigDecimal getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(BigDecimal moveCost) {
        Objects.requireNonNull(moveCost, "Move cost cannot be null");
        if (moveCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Move cost cannot be negative");
        }
        this.moveCost = moveCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        FreestandingProduct.saveFreestandingProducts();
    }

    public static void loadFreestandingProducts() {
        if(freestandingProducts.isEmpty()){
            freestandingProducts = Extent.loadClassList("./org/HomeApplianceStore/Products/FreestandingProduct.ser");
        }
    }

    public static void saveFreestandingProducts() {
        Extent.saveClassList("./org/HomeApplianceStore/Products/FreestandingProduct.ser", freestandingProducts);
    }

    public static List<FreestandingProduct> getFreestandingProducts() {
        return Extent.getImmutableClassList(freestandingProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FreestandingProduct)) return false;
        FreestandingProduct that = (FreestandingProduct) o;
        return Objects.equals(moveCost, that.moveCost) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveCost, product);
    }
}
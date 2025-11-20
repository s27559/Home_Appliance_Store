package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreestandingProduct implements Extent{

    private static ArrayList<FreestandingProduct> freestandingProducts =new ArrayList<FreestandingProduct>();

    private BigDecimal moveCost;

    public FreestandingProduct(BigDecimal moveCost) {
        if (moveCost == null || moveCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Move cost cannot be negative");
        }
        this.moveCost = moveCost.setScale(2, BigDecimal.ROUND_HALF_UP);

        addFreestandingProduct(this);
        saveFreestandingProducts();
    }

    private static void addFreestandingProduct(FreestandingProduct freestandingProduct) {
        if(!freestandingProducts.contains(freestandingProduct))
            freestandingProducts.add(freestandingProduct);
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
        return Objects.equals(moveCost, that.moveCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveCost);
    }
}
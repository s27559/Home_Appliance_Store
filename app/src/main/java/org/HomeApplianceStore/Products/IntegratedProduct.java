package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntegratedProduct implements Extent{

    private static ArrayList<IntegratedProduct> integratedProducts = new ArrayList<IntegratedProduct>();

    static {
        LoadIntegratedProducts();
    }

    private Product product;
    private BigDecimal integrationCost;
    private boolean mustBeDone;

    public IntegratedProduct(Product product, String name, BigDecimal integrationCost, boolean mustBeDone) {
        validateProduct(product);
        Objects.requireNonNull(name, "IntegratedProduct name cannot be null");
        if(integrationCost.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("IntegratedProduct cost cannot be negative");
        }
        this.integrationCost = integrationCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.mustBeDone = mustBeDone;

        setProduct(product);

        addIntegratedProduct(this);
        saveIntegratedProducts();
    }

    public void setProduct(Product newProduct) {
        Objects.requireNonNull(newProduct, "IntegratedProduct requires a Product.");

        if (Objects.equals(this.product, newProduct)) {
            return;
        }

        if (this.product != null) {
            this.product.removeIntegratedPart(this);
        }

        this.product = newProduct;

        this.product.addIntegratedProduct(this);
    }

    public void removeProduct(Product productToRemove) {
        if (!Objects.equals(this.product, productToRemove)) {
            return;
        }

        this.product.removeIntegratedPart(this);
        this.product = null;
    }

    private void validateProduct(Product product) {
        Objects.requireNonNull(product, "IntegratedProduct must be associated with a Product (1).");
    }
    private static void addIntegratedProduct(IntegratedProduct integratedProduct){
        if(!integratedProducts.contains(integratedProduct)){
            integratedProducts.add(integratedProduct);
        }
    }
    public Product getProduct() {
        return product;
    }
    public BigDecimal getIntegrationCost() {
        return integrationCost;
    }
    public void setIntegrationCost(BigDecimal integrationCost) {
        Objects.requireNonNull(integrationCost, "IntegratedProduct cost cannot be null");
        if(integrationCost.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("IntegratedProduct cost cannot be negative");
        }
        this.integrationCost = integrationCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        saveIntegratedProducts();
    }
    public boolean isMustBeDone() {
        return mustBeDone;
    }
    public void setMustBeDone(boolean mustBeDone) {
        this.mustBeDone = mustBeDone;
        saveIntegratedProducts();
    }

    public static void LoadIntegratedProducts(){
        integratedProducts = Extent.loadClassList("./org/HomeApplianceStore/Products/IntegratedProducts.ser");
    }

    public static void saveIntegratedProducts(){
        Extent.saveClassList("./org/HomeApplianceStore/Products/IntegratedProducts.ser", integratedProducts);
    }

    public static List<IntegratedProduct> getIntegratedProducts() {
        return Extent.getImmutableClassList(integratedProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegratedProduct)) return false;
        IntegratedProduct that = (IntegratedProduct) o;
        return mustBeDone  == that.mustBeDone && Objects.equals(integrationCost, that.integrationCost) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integrationCost, mustBeDone, product);
    }
}
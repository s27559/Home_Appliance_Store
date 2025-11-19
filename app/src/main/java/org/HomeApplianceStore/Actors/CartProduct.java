package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Products.Product;

public class CartProduct implements Extent{

    private static final String FILE_LOCATION = "./org/HomeApplianceStore/Actors/CartProduct.ser";

    private static ArrayList<CartProduct> cartProducts = new ArrayList<>();

    private long amountNew;
    private long amountUsed;

    private Product product;

    private Customer customer;

    public CartProduct(long amountNew, long amountUsed, Product product, Customer customer) {
        validateAmount(amountNew, "New amount");
        validateAmount(amountUsed, "Used amount");
        Objects.requireNonNull(product, "Product cannot be null for a cart item.");

        this.amountNew = amountNew;
        this.amountUsed = amountUsed;
        this.product = product;
        this.customer = customer;

        addCartProduct(this);
        saveCartProducts();
    }

    private void validateAmount(long value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative.");
        }
    }

    public static List<CartProduct> getCartProducts() {
        return Extent.getImmutableClassList(cartProducts);
    }

    public static void loadCartProducts(){
        List<CartProduct> loaded = Extent.loadClassList(FILE_LOCATION);
        cartProducts = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
    }

    public static void saveCartProducts(){
        Extent.saveClassList(FILE_LOCATION, cartProducts);
    }

    private static void addCartProduct(CartProduct cartProduct) {
        if (!cartProducts.contains(cartProduct)) {
            cartProducts.add(cartProduct);
        }
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null for a cart item.");
        this.product = product;
        saveCartProducts();
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
        saveCartProducts();
    }

    public long getAmountNew() {
        return amountNew;
    }
    public void setAmountNew(long amountNew) {
        validateAmount(amountNew, "New amount");
        this.amountNew = amountNew;
        saveCartProducts();
    }

    public long getAmountUsed() {
        return amountUsed;
    }
    public void setAmountUsed(long amountUsed) {
        validateAmount(amountUsed, "Used amount");
        this.amountUsed = amountUsed;
        saveCartProducts();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartProduct that)) return false;
        return amountNew == that.amountNew &&
                amountUsed == that.amountUsed &&
                Objects.equals(product, that.product) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, customer, amountNew, amountUsed);
    }
}
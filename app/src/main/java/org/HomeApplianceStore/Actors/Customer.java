package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Customer implements Extent {

    private static final String FILE_LOCATION = "./org/HomeApplianceStore/Actors/Customer.ser";

    private static ArrayList<Customer> customers = new ArrayList<>();

    private String name;
    private String email;
    private String address;
    private List<CartProduct> cartProducts;

    public Customer(String name, String email, String address) {
        validateName(name);
        validateEmail(email);

        this.name = name;
        this.email = email;
        this.address = address;
        this.cartProducts = new ArrayList<>();

        addCustomer(this);
        saveCustomers();
    }

    private void validateName(String name) {
        Objects.requireNonNull(name, "Customer name cannot be null.");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
    }

    private void validateEmail(String email) {
        Objects.requireNonNull(email, "Customer email cannot be null.");
        if (!email.contains("@") || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email is invalid.");
        }
    }

    public static List<Customer> getCustomers() {
        return Extent.getImmutableClassList(customers);
    }

    public static void loadCustomers(){
        List<Customer> loaded = Extent.loadClassList(FILE_LOCATION);
        customers = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
    }

    public static void saveCustomers(){
        Extent.saveClassList(FILE_LOCATION, customers);
    }

    private static void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        validateName(name);
        this.name = name;
        saveCustomers();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
        saveCustomers();
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
        saveCustomers();
    }

    public List<CartProduct> getCartProducts() {
        return Collections.unmodifiableList(cartProducts);
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        Objects.requireNonNull(cartProducts, "Cart products list cannot be null.");
        this.cartProducts = new ArrayList<>(cartProducts);
        saveCustomers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
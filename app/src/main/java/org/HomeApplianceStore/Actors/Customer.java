package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class Customer implements Extent {

    private static final String FILE_LOCATION = "./org/HomeApplianceStore/Actors/Customer.ser";

    private static ArrayList<Customer> customers = new ArrayList<>();

        static {
                loadCustomers();
        }

    private String name;
    private String email;
    private Address address;

        private ArrayList<CartProduct> cartProducts;
        private ArrayList<Order> BuyOrders;
        private ArrayList<Order> SellOrders;

        
    public Customer(String name, String email, Address address) {
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

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
        saveCustomers();
    }

        public void delete(){
                for(CartProduct cartProduct : getCartProducts()) cartProduct.delete();
                for (Order order : getBuyOrders()) {
                        order.delete();
                }
                for (Order order : getSellOrders()) {
                        order.delete();
                }
                customers.remove(this);
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

    public List<CartProduct> getCartProducts() {
        return Extent.getImmutableClassList(cartProducts);
    }

    public List<Order> getBuyOrders() {
        return Extent.getImmutableClassList(BuyOrders);
    }

    public List<Order> getSellOrders() {
        return Extent.getImmutableClassList(SellOrders);
    }

        public void addSellOrder(Order order) {
                if (BuyOrders.contains(order)) throw new InputMismatchException();
                SellOrders.add(order);
        }

        public void addBuyOrder(Order order) {
                if (SellOrders.contains(order)) throw new InputMismatchException();
                BuyOrders.add(order);
        }

        public void removeSellOrder(Order order){
                SellOrders.remove(order);
                order.delete();
        }

        public void removeBuyOrder(Order order){
                BuyOrders.remove(order);
                order.delete();
        }

    public void addCartProduct(CartProduct cartProduct) {
        cartProducts.add(cartProduct);
    }

    public void removeCartProduct(CartProduct cartProduct) {
       cartProducts.remove(cartProduct);
    }
}

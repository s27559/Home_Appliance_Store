package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;

public class Customer {
        private static ArrayList<Customer> customers = new ArrayList<>(); 

        private ArrayList<CartProduct> cartProducts = new ArrayList<>();

        public Customer() {
                addCustomer(this);
        }

        public static List<Customer> getCustomers() {
                return Extent.getImmutableClassList(customers);
        }

        public static void loadCustomers(){
                customers = Extent.loadClassList("./org/HomeApplianceStore/Actors/Customer.ser");
        }

        public static void saveCustomers(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Customer.ser", customers);
        }

        private static void addCustomer(Customer customer) {
                customers.add(customer);
        }

        public ArrayList<CartProduct> getCartProducts() {
                return cartProducts;
        }

        public void setCartProducts(ArrayList<CartProduct> cartProducts) {
                this.cartProducts = cartProducts;
        }
}

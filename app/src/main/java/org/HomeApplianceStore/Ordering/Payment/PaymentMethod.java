package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentMethod implements Extent {

        // extent
        private static ArrayList<PaymentMethod> methods = new ArrayList<>();

        private String name;

        private ArrayList<Order> orders = new ArrayList<>();

        public PaymentMethod(String name) {
                setName(name);
                addMethod(this);
        }

        private static void addMethod(PaymentMethod method) {
                if (method == null) {
                        throw new IllegalArgumentException("method cannot be null");
                }
                methods.add(method);
        }

        //basic attribute

        public String getName() {
                return name;
        }

        public void setName(String name) {
                if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Payment method name cannot be empty");
                }
                this.name = name.trim();
        }

        public List<Order> getOrders() {
                return Collections.unmodifiableList(orders);
        }

        public void addOrder(Order order) {
                if (order == null) {
                        throw new IllegalArgumentException("order cannot be null");
                }
                if (!orders.contains(order)) {
                        orders.add(order);
                }
        }

        public void removeOrder(Order order) {
                orders.remove(order);
        }

        public void delete() {
                for (Order order : new ArrayList<>(orders)) {
                        order.setPaymentMethod(null);
                }
                orders.clear();
                methods.remove(this);
        }

        public static void loadMethods() {
                methods = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/PaymentMethod.ser");
        }

        public static void saveMethods() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/PaymentMethod.ser", methods);
        }

        public static List<PaymentMethod> getMethods() {
                return Extent.getImmutableClassList(methods);
        }
}

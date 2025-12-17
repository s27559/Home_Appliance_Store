package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//SUPERCLASS

public class PaymentMethod implements Extent {

        private static ArrayList<PaymentMethod> methods = new ArrayList<>();

        private String name;

        //PaymentMethod (1) -> Order (0..*)
        private ArrayList<Order> orders = new ArrayList<>();

        //not abstract because {Disjoint, Incomplete}
        public PaymentMethod(String name) {
                setName(name);
                addMethod(this);
        }

        private static void addMethod(PaymentMethod method) {
                if (method == null) {
                        throw new IllegalArgumentException("Method cannot be null");
                }
                methods.add(method);
        }

        //attribute
        public String getName() {
                return name;
        }

        public void setName(String name) {
                if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty");
                }
                this.name = name.trim();
        }

        //association with Order
        public List<Order> getOrders() {
                return Collections.unmodifiableList(orders);
        }

        public void addOrder(Order order) {
                if (order == null) {
                        throw new IllegalArgumentException("Order cannot be null");
                }
                if (orders.contains(order)) {
                        return;
                }
                orders.add(order);

                //reverse connection
                //polymorphic call — Order works with PaymentMethod,
                //regardless of whether it is Card, Blik, Paypal or plain PaymentMethod
                if (order.getPaymentMethod() != this) {
                        order.setPaymentMethod(this);
                }
        }

        public void removeOrder(Order order) {
                orders.remove(order);
        }

        //extent handling
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
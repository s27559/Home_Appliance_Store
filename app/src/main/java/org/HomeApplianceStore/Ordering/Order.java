package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Payment.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Extent {

        private static ArrayList<Order> orders = new ArrayList<>();

        private LocalDate date;
        private boolean paidFor;
        private Boolean readyForPickUp;   // OPTIONAL [0..1]

        // Associations
        // 0..* deliveries for one order
        private List<Delivery> deliveries = new ArrayList<>();

        // 0..* product statuses for one order
        private List<ProductStatus> productStatuses = new ArrayList<>();
        private PaymentMethod paymentMethod;

        // 1 payment method for the order
        public Order(LocalDate date, boolean paidFor, Boolean readyForPickUp, PaymentMethod paymentMethod) {
                setDate(date);
                setPaidFor(paidFor);
                setReadyForPickUp(readyForPickUp);
                setPaymentMethod(paymentMethod);
                addOrder(this);                      // extent
        }

        private static void addOrder(Order order) {
                if (order == null) {
                        throw new IllegalArgumentException("order cannot be null");
                }
                orders.add(order);
        }

        //basic attributes

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                if (date == null) {
                        throw new IllegalArgumentException("date cannot be null");
                }
                this.date = date;
        }

        public boolean isPaidFor() {
                return paidFor;
        }

        public void setPaidFor(boolean paidFor) {
                this.paidFor = paidFor;
        }

        public Boolean getReadyForPickUp() {
                return readyForPickUp;
        }

        public void setReadyForPickUp(Boolean readyForPickUp) {
                this.readyForPickUp = readyForPickUp;
        }

        // cost derived from deliveries
        public BigDecimal getCost() {
                BigDecimal result = BigDecimal.ZERO;
                for (Delivery delivery : deliveries) {
                        if (delivery.getCost() != null) {
                                result = result.add(delivery.getCost());
                        }
                }
                return result;
        }

        // Associations handling
        public List<Delivery> getDeliveries() {
                return new ArrayList<>(deliveries);
        }

        public void addDelivery(Delivery delivery) {
                if (delivery == null) {
                        throw new IllegalArgumentException("delivery cannot be null");
                }
                if (delivery.getOrder() != null && delivery.getOrder() != this) {
                        throw new IllegalArgumentException("Delivery already assigned to a different order");
                }
                if (!deliveries.contains(delivery)) {
                        deliveries.add(delivery);
                        delivery.setOrder(this);
                }
        }

        public void removeDelivery(Delivery delivery) {
                if (delivery == null) {
                        return;
                }
                if (deliveries.remove(delivery)) {
                        if (delivery.getOrder() == this) {
                                delivery.setOrder(null);
                        }
                }
        }

        public List<ProductStatus> getProductStatuses() {
                return new ArrayList<>(productStatuses);
        }

        public void addProductStatus(ProductStatus status) {
                if (status == null) {
                        throw new IllegalArgumentException("status cannot be null");
                }
                if (status.getOrder() != null && status.getOrder() != this) {
                        throw new IllegalArgumentException("ProductStatus already assigned to a different order");
                }
                if (!productStatuses.contains(status)) {
                        productStatuses.add(status);
                        status.setOrder(this);
                }
        }

        public void removeProductStatus(ProductStatus status) {
                if (status == null) {
                        return;
                }
                if (productStatuses.remove(status)) {
                        if (status.getOrder() == this) {
                                status.setOrder(null);
                        }
                }
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
                if (paymentMethod == null) {
                        throw new IllegalArgumentException("paymentMethod cannot be null");
                }

                // nothing to do
                if (this.paymentMethod == paymentMethod) {
                        return;
                }

                // detach from old method
                if (this.paymentMethod != null) {
                        this.paymentMethod.removeOrder(this);
                }

                // set new method
                this.paymentMethod = paymentMethod;

                // ensure reverse link – add only if not already present
                paymentMethod.addOrder(this);   // no recursion because of check inside addOrder
        }

        public PaymentMethod getPaymentMethod() {
                return paymentMethod;
        }

        public static void loadOrders() {
                orders = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Order.ser");
        }

        public static void saveOrders() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Order.ser", orders);
        }

        public static List<Order> getOrders() {
                return Extent.getImmutableClassList(orders);
        }
}
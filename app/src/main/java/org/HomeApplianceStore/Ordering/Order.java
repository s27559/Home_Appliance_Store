package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order implements Extent {
        private static ArrayList<Order> orders= new ArrayList<Order>();

        static {
                loadOrders();
        }

        private LocalDate date;
        private boolean paidFor;
        private Boolean readyForPickUp;   // OPTIONAL [0..1]

        public Order(LocalDate date, boolean paidFor, Boolean readyForPickUp) {
                setDate(date);
                setPaidFor(paidFor);
                setReadyForPickUp(readyForPickUp); // may be null
                addOrder(this);
        }

        public BigDecimal getCost(){return new BigDecimal(0);}

        public static void addOrder(Order order){
                if (order == null) {
                        throw new IllegalArgumentException("Order cannot be null");
                }
                orders.add(order);
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                if (date == null) {
                        throw new IllegalArgumentException("date cannot be null");
                }
                if (date.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException("Order date cannot be in the future");
                }
                this.date = date;
        }

        public boolean isPaidFor() {
                return paidFor;
        }

        public void setPaidFor(boolean paidFor) {
                this.paidFor = paidFor;
        }

        public Optional<Boolean> getReadyForPickUp() {
                return Optional.of(readyForPickUp);
        }

        // Optional attribute → null allowed
        public void setReadyForPickUp(Boolean readyForPickUp) {
                this.readyForPickUp = readyForPickUp;
        }

        public static void loadOrders(){
                orders = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Order.ser");
        }

        public static void saveOrders(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Order.ser", orders);
        }

        public static List<Order> getOrders() {
                return Extent.getImmutableClassList(orders);
        }
}

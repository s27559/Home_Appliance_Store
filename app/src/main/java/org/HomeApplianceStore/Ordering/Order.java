package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Extent {
        private static ArrayList<Order> orders= new ArrayList<Order>();

        private LocalDate date;
        private boolean paidFor;
        private boolean readyForPickUp;
        public Order(LocalDate date, boolean paidFor, boolean readyForPickUp) {
                this.setDate(date);
                this.setPaidFor(paidFor);
                this.setReadyForPickUp(readyForPickUp);
        }
        public BigDecimal getCost(){return new BigDecimal(0);}
        private void addDelivery(){
                orders.add(this);;
        }
        public LocalDate getDate() {
                return date;
        }
        public void setDate(LocalDate date) {
                this.date = date;
        }
        public boolean isPaidFor() {
                return paidFor;
        }
        public void setPaidFor(boolean paidFor) {
                this.paidFor = paidFor;
        }
        public boolean isReadyForPickUp() {
                return readyForPickUp;
        }
        public void setReadyForPickUp(boolean readyForPickUp) {
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

package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Extent {
        private static ArrayList<Order> orders= new ArrayList<Order>();

        private LocalDate date;
        private boolean paidFor;
        private boolean readyForPickUp;
        // cost
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
        public static void loadDeliveries(){
                orders = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Order.ser");
        }

        public static void saveDeliveries(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Order.ser", orders);
        }

        public static List<Order> getCategories() {
                return Extent.getImmutableClassList(orders);
        }
}

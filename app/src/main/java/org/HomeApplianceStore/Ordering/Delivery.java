package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Delivery implements Extent {
        private static ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

        private LocalDate sendDate;
        private LocalDate reciveDate;
        private BigDecimal cost;
        private boolean recived;
        private String trackingNumber;

        public LocalDate getSendDate() {
                return sendDate;
        }
        public void setSendDate(LocalDate sendDate) {
                this.sendDate = sendDate;
        }
        public LocalDate getReciveDate() {
                return reciveDate;
        }
        public void setReciveDate(LocalDate reciveDate) {
                this.reciveDate = reciveDate;
        }
        public BigDecimal getCost() {
                return cost;
        }
        public void setCost(BigDecimal cost) {
                this.cost = cost;
        }
        public boolean isRecived() {
                return recived;
        }
        public void setRecived(boolean recived) {
                this.recived = recived;
        }
        public String getTrackingNumber() {
                return trackingNumber;
        }
        public void setTrackingNumber(String trackingNumber) {
                this.trackingNumber = trackingNumber;
        }
        public static void loadDeliveries(){
                deliveries = Extent.loadClassList("./org/HomeApplianceStore/Products/Category.ser");
        }

        public static void saveDeliveries(){
                Extent.saveClassList("./org/HomeApplianceStore/Products/Category.ser", deliveries);
        }

        public static List<Delivery> getCategories() {
                return Extent.getImmutableClassList(deliveries);
        }
}

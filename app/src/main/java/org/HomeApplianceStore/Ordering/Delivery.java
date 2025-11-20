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

        public Delivery(LocalDate sendDate,
                        LocalDate reciveDate,
                        BigDecimal cost,
                        boolean recived,
                        String trackingNumber) {

                this.setSendDate(sendDate);
                this.setReciveDate(reciveDate);
                this.setCost(cost);
                this.setRecived(recived);
                this.setTrackingNumber(trackingNumber);
                addDelivery(this);
        }

        public static void addDelivery(Delivery delivery){
                if (delivery == null) {
                        throw new IllegalArgumentException("Delivery cannot be null");
                }
                deliveries.add(delivery);
        }
        public LocalDate getSendDate() {
                return sendDate;
        }
        public void setSendDate(LocalDate sendDate) {
                if (sendDate == null) {
                        throw new IllegalArgumentException("sendDate cannot be null");
                }
                if (sendDate.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException("sendDate cannot be in the future");
                }
                this.sendDate = sendDate;
                if (this.reciveDate != null && this.reciveDate.isBefore(this.sendDate)) {
                        throw new IllegalArgumentException("receiveDate cannot be before sendDate");
                }
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
                if (cost == null) {
                        throw new IllegalArgumentException("cost cannot be null");
                }
                if (cost.signum() < 0) {
                        throw new IllegalArgumentException("cost cannot be negative");
                }
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
                if (trackingNumber == null || trackingNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("trackingNumber cannot be empty");
        }
                this.trackingNumber = trackingNumber.trim();

        }
        public static void loadDeliveries(){
                deliveries = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Delivery.ser");
        }

        public static void saveDeliveries(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Delivery.ser", deliveries);
        }

        public static List<Delivery> getDeliveries() {
                return Extent.getImmutableClassList(deliveries);
        }
}
package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Delivery implements Extent {

        private static ArrayList<Delivery> deliveries = new ArrayList<>();

        private LocalDate sendDate;
        private LocalDate receiveDate;
        private BigDecimal cost;
        private boolean received;
        private String trackingNumber;
        private String deliveryCompany;

        // Association to Order: each delivery is for exactly one order (1)
        private Order order;

        public Delivery(Order order, LocalDate sendDate,
                        LocalDate receiveDate,
                        BigDecimal cost,
                        boolean received,
                        String trackingNumber, String fedEx) {

                if (this.order == null) {
                        throw new IllegalArgumentException("Order cannot be null for Delivery");
                }

                setSendDate(sendDate);
                setReceiveDate(receiveDate);
                setCost(cost);
                setReceived(received);
                setTrackingNumber(trackingNumber);
                setDeliveryCompany(deliveryCompany);

                addDelivery(this);

                this.order.addDelivery(this);
        }

        private static void addDelivery(Delivery delivery) {
                if (delivery == null) {
                        throw new IllegalArgumentException("delivery cannot be null");
                }
                deliveries.add(delivery);
        }

        //basic attributes

        public LocalDate getSendDate() {
                return sendDate;
        }

        public void setSendDate(LocalDate sendDate) {
                if (sendDate == null) {
                        throw new IllegalArgumentException("sendDate cannot be null");
                }
                this.sendDate = sendDate;
        }

        public LocalDate getReceiveDate() {
                return receiveDate;
        }

        public void setReceiveDate(LocalDate receiveDate) {
                if (receiveDate != null && sendDate != null && receiveDate.isBefore(sendDate)) {
                        throw new IllegalArgumentException("receiveDate cannot be before sendDate");
                }
                this.receiveDate = receiveDate;
        }

        public BigDecimal getCost() {
                return cost;
        }

        public void setCost(BigDecimal cost) {
                if (cost == null || cost.signum() < 0) {
                        throw new IllegalArgumentException("cost must be non-negative");
                }
                this.cost = cost;
        }

        public boolean isReceived() {
                return received;
        }

        public void setReceived(boolean received) {
                this.received = received;
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

        public String getDeliveryCompany() {
                return deliveryCompany;
        }

        public void setDeliveryCompany(String deliveryCompany) {
                if (deliveryCompany == null || deliveryCompany.trim().isEmpty()) {
                        throw new IllegalArgumentException("deliveryCompany cannot be empty");
                }
                this.deliveryCompany = deliveryCompany.trim();
        }

        //association with Order

        public Order getOrder() {
                return order;
        }

        void setOrder(Order order) {
                this.order = order;
        }

        public static void loadDeliveries() {
                deliveries = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Delivery.ser");
        }

        public static void saveDeliveries() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Delivery.ser", deliveries);
        }

        public static List<Delivery> getDeliveries() {
                return Extent.getImmutableClassList(deliveries);
        }

        public void delete() {
            deliveries.remove(this);
                saveDeliveries();
        }
}

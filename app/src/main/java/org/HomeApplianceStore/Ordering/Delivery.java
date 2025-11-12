package org.HomeApplianceStore.Ordering;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delivery implements Serializable {

        private static final long serialVersionUID = 1L;

        private static List<Delivery> extent = new ArrayList<>();

        private LocalDate sendDate;
        private LocalDate receiveDate;
        private BigDecimal cost;
        private boolean received;
        private String trackingNumber;
        private String deliveryCompany;

        public Delivery() {
                addToExtent(this);
        }

        public Delivery(LocalDate sendDate,
                        LocalDate receiveDate,
                        BigDecimal cost,
                        boolean received,
                        String trackingNumber,
                        String deliveryCompany) {
                this.sendDate = sendDate;
                this.receiveDate = receiveDate;
                this.cost = cost;
                this.received = received;
                this.trackingNumber = trackingNumber;
                this.deliveryCompany = deliveryCompany;
                addToExtent(this);
        }

        private static void addToExtent(Delivery delivery) {
                if (delivery == null) {
                        throw new IllegalArgumentException("Delivery cannot be null");
                }
                extent.add(delivery);
        }

        public static List<Delivery> getExtent() {
                return Collections.unmodifiableList(extent);
        }

        public static boolean saveExtent(String path) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
                        out.writeObject(extent);
                        return true;
                } catch (IOException e) {
                        return false;
                }
        }

        @SuppressWarnings("unchecked")
        public static boolean loadExtent(String path) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                        Object obj = in.readObject();
                        if (obj instanceof List<?>) {
                                extent = (List<Delivery>) obj;
                                return true;
                        } else {
                                extent.clear();
                                return false;
                        }
                } catch (Exception e) {
                        extent.clear();
                        return false;
                }
        }

        public LocalDate getSendDate() {
                return sendDate;
        }

        public void setSendDate(LocalDate sendDate) {
                this.sendDate = sendDate;
        }

        public LocalDate getReceiveDate() {
                return receiveDate;
        }

        public void setReceiveDate(LocalDate receiveDate) {
                this.receiveDate = receiveDate;
        }

        public BigDecimal getCost() {
                return cost;
        }

        public void setCost(BigDecimal cost) {
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
                this.trackingNumber = trackingNumber;
        }

        public String getDeliveryCompany() {
                return deliveryCompany;
        }

        public void setDeliveryCompany(String deliveryCompany) {
                this.deliveryCompany = deliveryCompany;
        }
}

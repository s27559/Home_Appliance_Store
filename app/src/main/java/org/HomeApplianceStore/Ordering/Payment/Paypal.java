package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

//SUBCLASS

public class Paypal extends PaymentMethod implements Extent {

        private static ArrayList<Paypal> paypals = new ArrayList<>();

        private String paypalAccountId;

        public Paypal(String paypalAccountId) {
                //calls PaymentMethod constructor
                //chis is inheritance, not association
                super("Paypal");
                setPaypalAccountId(paypalAccountId);
                addPaypal(this);
        }

        private static void addPaypal(Paypal paypal) {
                if (paypal == null) {
                        throw new IllegalArgumentException("Paypal cannot be null");
                }
                paypals.add(paypal);
        }

        public String getPaypalAccountId() {
                return paypalAccountId;
        }

        public void setPaypalAccountId(String paypalAccountId) {
                if (paypalAccountId == null || paypalAccountId.trim().isEmpty()) {
                        throw new IllegalArgumentException("Paypal Account Id cannot be empty");
                }
                this.paypalAccountId = paypalAccountId.trim();
        }

        @Override
        public void delete() {
                super.delete();
                paypals.remove(this);
        }

        //extent handling
        public static void loadPaypals() {
                paypals = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Paypal.ser");
        }

        public static void savePaypals() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Paypal.ser", paypals);
        }

        public static List<Paypal> getPaypals() {
                return Extent.getImmutableClassList(paypals);
        }
}
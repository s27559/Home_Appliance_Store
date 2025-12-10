package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;
import java.util.ArrayList;
import java.util.List;

public class Paypal extends PaymentMethod implements Extent {

        private static ArrayList<Paypal> paypals = new ArrayList<>();

        static {
                loadPaypals();
        }
        private String paypalAccountId;

        public Paypal(String name, String paypalAccountId) {
                super(name);
                this.setPaypalAccountId(paypalAccountId);
                addPaypal(this);
        }

        public static void addPaypal(Paypal paypal){
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
                        throw new IllegalArgumentException("PayPal account ID cannot be empty");
                }
                if (!paypalAccountId.contains("@")) {
                        throw new IllegalArgumentException("PayPal account ID should look like an email address");
                }
                this.paypalAccountId = paypalAccountId.trim();
        }
        public static void loadPaypals(){
                paypals = Extent.loadClassList("Paypal.ser");
        }

        public static void savePaypals(){
                Extent.saveClassList("Paypal.ser", paypals);
        }

        public static List<Paypal> getPaypals() {
                return Extent.getImmutableClassList(paypals);
        }

}
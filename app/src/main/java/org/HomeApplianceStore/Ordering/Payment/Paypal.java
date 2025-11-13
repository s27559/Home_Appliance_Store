package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Paypal extends PaymentMethod implements Extent {
        private static ArrayList<Paypal> paypals = new ArrayList<Paypal>();


        private String paypalAccountId;

        public Paypal(String paypalAccountId) {
                this.setPaypalAccountId(paypalAccountId);

        }
        private void addPaypal(){
                paypals.add(this);
        }

        public String getPaypalAccountId() {
                return paypalAccountId;
        }

        public void setPaypalAccountId(String paypalAccountId) {
                this.paypalAccountId = paypalAccountId;
        }
        public static void loadPaypals(){
                paypals = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Paypal.ser");
        }

        public static void savePaypals(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Paypal.ser", paypals);
        }

        public static List<Paypal> getPaypals() {
                return Extent.getImmutableClassList(paypals);
        }

}

package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Delivery;

import java.util.ArrayList;
import java.util.List;

public class Blik extends PaymentMethod implements Extent {
        private static ArrayList<Blik> bliks = new ArrayList<Blik>();

        private String transactionId;

        public Blik(String transactionId, String name) {
                super(name);
                this.setTransactionId(transactionId);
                addBlik(this);
        }

        private static void addBlik(Blik blik){
                bliks.add(blik);
        }

        public String getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(String transactionId) {
                this.transactionId = transactionId;
        }
        public static void loadBliks(){
                bliks = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Blik.ser");
        }

        public static void saveBliks(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Blik.ser", bliks);
        }

        public static List<Blik> getBliks() {
                return Extent.getImmutableClassList(bliks);
        }
}

package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

//SUBCLASS

public class Blik extends PaymentMethod implements Extent {

        private static ArrayList<Blik> bliks = new ArrayList<>();

        private String code;
        private String transactionId;

        public Blik(String code, String transactionId) {
                //calls PaymentMethod constructor
                //(inheritance constructor chaining)
                super("Blik");
                setCode(code);
                setTransactionId(transactionId);
                addBlik(this);
        }

        private static void addBlik(Blik blik) {
                if (blik == null) {
                        throw new IllegalArgumentException("Blik cannot be null");
                }
                bliks.add(blik);
        }

        public String getCode() {
                return code;
        }

        //code=6 digits
        public void setCode(String code) {
                if (code == null || !code.matches("\\d{6}")) {
                        throw new IllegalArgumentException("Blik code must consist of 6 digits");
                }
                this.code = code;
        }

        public String getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(String transactionId) {
                if (transactionId == null || transactionId.trim().isEmpty()) {
                        throw new IllegalArgumentException("Transaction Id cannot be empty");
                }
                this.transactionId = transactionId.trim();
        }

        @Override
        public void delete() {
                super.delete();
                bliks.remove(this);
        }

        //extent handling
        public static void loadBliks() {
                bliks = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Blik.ser");
        }

        public static void saveBliks() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Blik.ser", bliks);
        }

        public static List<Blik> getBliks() {
                return Extent.getImmutableClassList(bliks);
        }
}
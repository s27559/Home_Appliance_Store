package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Blik extends PaymentMethod implements Extent {
        private static ArrayList<Blik> bliks = new ArrayList<Blik>();

        static {
                loadBliks();
        }

        private String code;
        private String transactionId;

        public Blik(String name, String code, String transactionId) {
                super(name);
                this.setCode(code);
                this.setTransactionId(transactionId);
                addBlik(this);
        }

        private static void addBlik(Blik blik){
                if (blik == null) {
                        throw new IllegalArgumentException("Blik cannot be null");
                }
                bliks.add(blik);
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                if (code == null || code.trim().isEmpty()) {
                        throw new IllegalArgumentException("Blik code cannot be empty");
                }
                if (!code.matches("\\d{6}")) {
                        throw new IllegalArgumentException("Blik code must consist of exactly 6 digits");
                }
                this.code = code;
        }

        public String getTransactionId() {
                return transactionId;
        }

        public void setTransactionId(String transactionId) {
                if (transactionId == null || transactionId.trim().isEmpty()) {
                        throw new IllegalArgumentException("Transaction ID cannot be empty");
                }
                this.transactionId = transactionId.trim();
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

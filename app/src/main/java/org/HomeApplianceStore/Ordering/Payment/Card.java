package org.HomeApplianceStore.Ordering.Payment;

public class Card {

        private String cardNum;
        private String cvv;
        private String ownerName;
        public String getCardNum() {
                return cardNum;
        }
        public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
        }
        public String getCvv() {
                return cvv;
        }
        public void setCvv(String cvv) {
                this.cvv = cvv;
        }
        public String getOwnerName() {
                return ownerName;
        }
        public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
        }
}

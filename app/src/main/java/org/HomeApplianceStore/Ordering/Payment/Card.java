package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Card extends PaymentMethod implements Extent {
        private static ArrayList<Card> cards = new ArrayList<Card>();

        static {
                loadCards();
        }

        private String cardNum;
        private String cvv;
        private String ownerName;

        public Card(String name, String cardNum, String cvv, String ownerName) {
                super(name);
                this.setCardNum(cardNum);
                this.setCvv(cvv);
                this.setOwnerName(ownerName);
                addCard(this);
        }

        private static void addCard(Card card){
                if (card == null) {
                        throw new IllegalArgumentException("Card cannot be null");
                }
                cards.add(card);
        }
        public String getCardNum() {
                return cardNum;
        }

        public void setCardNum(String cardNum) {
                if (cardNum == null || cardNum.trim().isEmpty()) {
                        throw new IllegalArgumentException("Card number cannot be empty");
                }
                this.cardNum = cardNum;
        }
        public String getCvv() {
                return cvv;
        }
        public void setCvv(String cvv) {
                if (cvv == null || cvv.trim().isEmpty()) {
                        throw new IllegalArgumentException("CVV cannot be empty");
                }
                this.cvv = cvv;
        }
        public String getOwnerName() {
                return ownerName;
        }
        public void setOwnerName(String ownerName) {
                if (ownerName == null || ownerName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Owner name cannot be empty");
                }
                this.ownerName = ownerName;
        }
        public static void loadCards(){
                cards = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Card.ser");
        }

        public static void saveCards(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Card.ser", cards);
        }

        public static List<Card> getCards() {
                return Extent.getImmutableClassList(cards);
        }
}

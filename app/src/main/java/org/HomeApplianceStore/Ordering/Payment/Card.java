package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Card extends PaymentMethod implements Extent {
        private static ArrayList<Card> cards = new ArrayList<Card>();

        private String cardNum;
        private String cvv;
        private String ownerName;

        public Card(String cardNum, String cvv, String ownerName) {
                super(cardNum);
                this.setCardNum(cardNum);
                this.setCvv(cvv);
                this.setOwnerName(ownerName);
                addCard(this);
        }

        private static void addCard(Card card){
                cards.add(card);
        }
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

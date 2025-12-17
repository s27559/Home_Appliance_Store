package org.HomeApplianceStore.Ordering.Payment;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

//SUBCLASS

public class Card extends PaymentMethod implements Extent {

        private static ArrayList<Card> cards = new ArrayList<>();

        static {
                loadCards();
        }

        private String cardnum;
        private String cvv;
        private String ownerName;

        public Card(String cardnum, String cvv, String ownerName) {
                //calls constructor of PaymentMethod (the superclass)
                super("Card");
                setCardnum(cardnum);
                setCvv(cvv);
                setOwnerName(ownerName);
                addCard(this);
        }

        private static void addCard(Card card) {
                if (card == null) {
                        throw new IllegalArgumentException("Card cannot be null");
                }
                cards.add(card);
        }

        public String getCardnum() {
                return cardnum;
        }

        public void setCardnum(String cardnum) {
                if (cardnum == null || cardnum.trim().isEmpty()) {
                        throw new IllegalArgumentException("Card Number cannot be empty");
                }
                this.cardnum = cardnum.trim();
        }

        public String getCvv() {
                return cvv;
        }

        public void setCvv(String cvv) {
                if (cvv == null || !cvv.matches("\\d{3}")) {
                        throw new IllegalArgumentException("cvv must be exactly 3 digits");
                }
                this.cvv = cvv;
        }

        public String getOwnerName() {
                return ownerName;
        }

        public void setOwnerName(String ownerName) {
                if (ownerName == null || ownerName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Owner Name cannot be empty");
                }
                this.ownerName = ownerName.trim();
        }

        //extent handling

        public static void loadCards() {
                cards = Extent.loadClassList("./org/HomeApplianceStore/Ordering/Payment/Card.ser");
        }

        public static void saveCards() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/Payment/Card.ser", cards);
        }

        public static List<Card> getCards() {
                return Extent.getImmutableClassList(cards);
        }
}
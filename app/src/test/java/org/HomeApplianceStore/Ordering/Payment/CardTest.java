package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void creatingValidCardShouldSucceed() {
        Card card = new Card("Card",
                "1234 5678 9012 3456",
                "123",
                "John Doe");

        assertEquals("1234567890123456", card.getCardNum());
        assertEquals("123", card.getCvv());
        assertEquals("John Doe", card.getOwnerName());
    }

    @Test
    void emptyCardNumberShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "   ", "123", "John Doe")
        );
    }

    @Test
    void cardNumberWithLettersShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "1234-ABCD-", "123", "John Doe")
        );
    }

    @Test
    void cardNumberTooShortShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "", "123", "John Doe")
        );
    }

    @Test
    void emptyCvvShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "", "   ", "John Doe")
        );
    }

    @Test
    void cvvWithWrongLengthShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "", "12", "John Doe")
        );
    }

    @Test
    void emptyOwnerNameShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "1234567890123456", "123", "   ")
        );
    }

    @Test
    void extentShouldUpdateAndPersistForCards() {
        int sizeBefore = Card.getCards().size();

        Card card = new Card("Card",
                "1111 2222 3333 4444",
                "321",
                "Jane Doe");

        int sizeAfterCreate = Card.getCards().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<Card> immutableList = Card.getCards();
        assertThrows(UnsupportedOperationException.class, () -> immutableList.add(card));

        Card.saveCards();
        Card.loadCards();

        int sizeAfterReload = Card.getCards().size();
        assertEquals(sizeAfterCreate, sizeAfterReload);
    }
}
package org.HomeApplianceStore.Ordering.Payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void creatingValidCardShouldSucceed() {
        Card card = new Card("Card",
                "1234 5678 9012 3456",
                "123",
                "John Doe");

        assertEquals("1234567890123456", card.getCardNum()); // пробелы должны быть убраны
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
                new Card("Card", "1234-ABCD-5678", "123", "John Doe")
        );
    }

    @Test
    void cardNumberTooShortShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "12345678901", "123", "John Doe")
        );
    }

    @Test
    void emptyCvvShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "1234567890123456", "   ", "John Doe")
        );
    }

    @Test
    void cvvWithWrongLengthShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "1234567890123456", "12", "John Doe")
        );
    }

    @Test
    void emptyOwnerNameShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Card("Card", "1234567890123456", "123", "   ")
        );
    }
}

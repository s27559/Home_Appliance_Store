package org.HomeApplianceStore.Ordering;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void creatingValidDeliveryShouldSucceed() {
        LocalDate send = LocalDate.now().minusDays(2);
        LocalDate receive = LocalDate.now().minusDays(1);
        BigDecimal cost = new BigDecimal("20.50");

        Delivery delivery = new Delivery(send, receive, cost, true, "TRK123");

        assertEquals(send, delivery.getSendDate());
        assertEquals(receive, delivery.getReciveDate());
        assertEquals(cost, delivery.getCost());
        assertTrue(delivery.isRecived());
        assertEquals("TRK123", delivery.getTrackingNumber());
    }

    @Test
    void sendDateInFutureShouldThrow() {
        LocalDate future = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(future, future, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void receiveDateBeforeSendDateShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now().minusDays(2);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void receiveDateInFutureShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.TEN, false, "TRK1")
        );
    }

    @Test
    void negativeCostShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, new BigDecimal("-1.00"), false, "TRK1")
        );
    }

    @Test
    void nullCostShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, null, false, "TRK1")
        );
    }

    @Test
    void emptyTrackingNumberShouldThrow() {
        LocalDate send = LocalDate.now().minusDays(1);
        LocalDate receive = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () ->
                new Delivery(send, receive, BigDecimal.ONE, false, "   ")
        );
    }
}
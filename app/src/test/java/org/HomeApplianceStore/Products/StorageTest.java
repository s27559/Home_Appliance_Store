package org.HomeApplianceStore.Products;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final long VALID_REPAIR = 5;
    private static final long VALID_USED = 10;
    private static final long VALID_NEW = 20;

    @Test
    void constructor_works_andInitializesFields_whenValid() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);

        assertEquals(VALID_REPAIR, storage.getInRepairAmmount());
        assertEquals(VALID_USED, storage.getUsedStock());
        assertEquals(VALID_NEW, storage.getNewStock());
    }

    @Test
    void constructor_works_whenStockIsZero() {
        Storage storage = new Storage(0, 0, 0);

        assertEquals(0, storage.getInRepairAmmount());
        assertEquals(0, storage.getUsedStock());
        assertEquals(0, storage.getNewStock());
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenRepairIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage(-1, VALID_USED, VALID_NEW);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenUsedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage(VALID_REPAIR, -1, VALID_NEW);
        });
    }

    @Test
    void constructor_throwsIllegalArgumentException_whenNewIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Storage(VALID_REPAIR, VALID_USED, -1);
        });
    }

    @Test
    void setInRepairAmmount_works_whenValid() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);
        long newValue = 7;

        storage.setInRepairAmmount(newValue);

        assertEquals(newValue, storage.getInRepairAmmount());
    }

    @Test
    void setInRepairAmmount_throwsIllegalArgumentException_whenNegative() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);

        assertThrows(IllegalArgumentException.class, () -> storage.setInRepairAmmount(-1));

        assertEquals(VALID_REPAIR, storage.getInRepairAmmount());
    }

    @Test
    void setUsedStock_works_whenValid() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);
        long newValue = 15;

        storage.setUsedStock(newValue);

        assertEquals(newValue, storage.getUsedStock());
    }

    @Test
    void setUsedStock_throwsIllegalArgumentException_whenNegative() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);

        assertThrows(IllegalArgumentException.class, () -> storage.setUsedStock(-1));

        assertEquals(VALID_USED, storage.getUsedStock());
    }

    @Test
    void setNewStock_works_whenValid() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);
        long newValue = 25;

        storage.setNewStock(newValue);

        assertEquals(newValue, storage.getNewStock());
    }

    @Test
    void setNewStock_throwsIllegalArgumentException_whenNegative() {
        Storage storage = new Storage(VALID_REPAIR, VALID_USED, VALID_NEW);

        assertThrows(IllegalArgumentException.class, () -> storage.setNewStock(-1));

        assertEquals(VALID_NEW, storage.getNewStock());
    }

    @Test
    void addStorage_preventsDuplicatesBasedOnEquals_identityLogic() {
        Storage s1 = new Storage(1, 1, 1);
        Storage s2 = new Storage(1, 1, 1);
        Storage s3 = new Storage(2, 2, 2);

        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
    }

    @Test
    void equalsAndHashCode_shouldBeBasedOnAllStockLevels() {
        Storage s1 = new Storage(10, 5, 20);
        Storage s2 = new Storage(10, 5, 20);
        Storage s3 = new Storage(11, 5, 20);

        assertTrue(s1.equals(s2));
        assertEquals(s1.hashCode(), s2.hashCode());

        assertFalse(s1.equals(s3));
    }
}
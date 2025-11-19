package org.HomeApplianceStore.Managment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.HomeApplianceStore.Address;

public class StoreTest {

    @Test
    void testConstructorSetsAttributesAndAddsToExtent() {
        Address address = new Address();
        address.setStreet("Main St");

        Store store = new Store(address);

        assertSame(address, store.getLocationAddress());

        List<Store> extent = Store.getStores();
        assertTrue(extent.contains(store));
    }

    @Test
    void testSetterUpdatesAttribute() {
        Store store = new Store(new Address());
        Address newAddress = new Address();

        store.setLocationAddress(newAddress);

        assertSame(newAddress, store.getLocationAddress());
    }
}
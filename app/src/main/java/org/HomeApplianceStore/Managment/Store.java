package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

import java.util.*;

public class Store implements Extent {
        private Set<ClosedFor> _closedForEvents = new HashSet<>(); // COlelction to hold the 'Many' side of the association
        private Address locationAddress;

        private static ArrayList<Store> stores = new ArrayList<Store>();
        private static final String FILE_LOCATION = "Store.ser";

        static {
                loadStores();
        }

        public Store(Address locationAddress){
                Objects.requireNonNull(locationAddress, "Missing Address object.");
                this.locationAddress = locationAddress;
                addStore(this);
                saveStore();
        }

        private static void addStore(Store store){
            if(!stores.contains(store))
                stores.add(store);
            saveStore();
        }

        public Address getLocationAddress() {
                return locationAddress;
        }

        public void setLocationAddress(Address locationAddress) {
                Objects.requireNonNull(locationAddress, "Address cannot be null.");
                this.locationAddress = locationAddress;
                saveStore();
        }

        // ClosedFor association methods Store 1 <-> * ClosedFor
        // Getter for ClosedFor events
        public Set<ClosedFor> getClosedForEvents() {
                return Collections.unmodifiableSet(_closedForEvents);
        }
        // Adds a ClosedFor event to the Store
        public void  addClosedForEvent(ClosedFor event){
            if (event == null) {
                throw new IllegalArgumentException("ClosedFor event cannot be null.");
            }
            if (!_closedForEvents.contains(event)) {
                _closedForEvents.add(event);

                if (event.getStore() != this) {
                    event.setStore(this);
                }
                saveStore(); // Persist changes
            }
        }
        // Removes a ClosedFor event from the Store
        public void removeClosedFor(ClosedFor event) {
            if (event == null) return;

            if (_closedForEvents.contains(event)) {
                _closedForEvents.remove(event);

                if (event.getStore() == this) {
                    event.delete(); // This removes the event from the extent
                }
                saveStore();
            }
        }

        // extent methods
        public static void loadStores(){
            stores = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveStore(){
                Extent.saveClassList(FILE_LOCATION, stores);
        }

        public static List<Store> getStores(){
                return Extent.getImmutableClassList(stores);
        }

        // updated for deleting all associated ClosedFor events
        public void delete(){
            for (ClosedFor event : new ArrayList<>(_closedForEvents)) {
                removeClosedFor(event);
            }
            stores.remove(this);
            saveStore();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Store)) return false;
            Store other = (Store) o;
            return Objects.equals(locationAddress, other.locationAddress);
        }

        @Override
        public int hashCode() {
            return Objects.hash(locationAddress);
        }
}

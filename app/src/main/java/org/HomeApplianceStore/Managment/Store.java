package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Store implements Extent {
        private Address locationAddress;

        private static ArrayList<Store> stores = new ArrayList<Store>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Store.ser";

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

        public static void loadStores(){
            stores = Extent.loadClassList(FILE_LOCATION);
        }

        public static void saveStore(){
                Extent.saveClassList(FILE_LOCATION, stores);
        }

        public static List<Store> getStores(){
                return Extent.getImmutableClassList(stores);
        }

        public void delete(){
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

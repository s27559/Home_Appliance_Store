package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Store implements Extent {
        private Address locationAddress;

        private static ArrayList<Store> stores = new ArrayList<Store>();
        private static final String FILE_LOCATION = "./org/HomeApplianceStore/Managment/Store.ser";


        public Store(Address locationAddress){
                this.locationAddress = locationAddress;
                addStore(this);
        }

        private static void addStore(Store store){
                stores.add(store);
        }

        public Address getLocationAddress() {
                return locationAddress;
        }

        public void setLocationAddress(Address locationAddress) {
                this.locationAddress = locationAddress;
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
        }
}

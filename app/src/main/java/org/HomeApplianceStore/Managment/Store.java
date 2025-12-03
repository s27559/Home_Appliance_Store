package org.HomeApplianceStore.Managment;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Products.Storage;

import java.util.*;

public class Store implements Extent {
        // ASssociations
        private Set<ClosedFor> _closedForEvents = new HashSet<>(); // Composition
        private Set<Shift> _shifts = new HashSet<>();
        private Set<Contract> _contracts = new HashSet<>();
        private Set<Storage> _storages = new HashSet<>();

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

        // ClosedFor Composition
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
                // reverse connection automatic update
                if (event.getStore() != this) {
                    event.setStore(this);
                }
                saveStore(); // Persist changes
            }
        }
        // Removes a ClosedFor event from the Store
        public void removeClosedFor(ClosedFor event) {
            if (event == null) {;
                throw new IllegalArgumentException("ClosedFor event cannot be null.");
            }

            if (_closedForEvents.contains(event)) {
                _closedForEvents.remove(event);

                // Composition: Part dies with Whole or removal
                if (event.getStore() == this) {
                    event.delete(); // This removes the event from the extent
                }
                saveStore();
            }
        }

        // Shift Association
        public Set<Shift> getShifts() {
            return Collections.unmodifiableSet(_shifts);
        }

        public void addShift(Shift shift) {
            if (shift == null) throw new IllegalArgumentException("Shift cannot be null.");
            if (!_shifts.contains(shift)) {
                _shifts.add(shift);
                // Reverse connection check
                if (shift.getStore() != this) {
                    shift.setStore(this);
                }
                saveStore();
            }
        }

        public void removeShift(Shift shift) {
            if (shift == null) return;
            if (_shifts.contains(shift)) {
                _shifts.remove(shift);
                if (shift.getStore() == this) {
                    shift.delete();
                }
                saveStore();
            }
        }

        // Contract Association
        public Set<Contract> getContracts() {
            return Collections.unmodifiableSet(_contracts);
        }

        public void addContract(Contract contract) {
            if (contract == null) throw new IllegalArgumentException("Contract cannot be null.");
            if (!_contracts.contains(contract)) {
                _contracts.add(contract);
                if (contract.getStore() != this) {
                    contract.setStore(this);
                }
                saveStore();
            }
        }

        public void removeContract(Contract contract) {
            if (contract == null) return;
            if (_contracts.contains(contract)) {
                _contracts.remove(contract);
                if (contract.getStore() == this) {
                    contract.delete();
                }
                saveStore();
            }
        }

        // Storage Association
        public Set<Storage> getStorages() {
            return Collections.unmodifiableSet(_storages);
        }
        public void addStorage(Storage storage) {
            if (storage == null) throw new IllegalArgumentException("Storage cannot be null.");
            if (!_storages.contains(storage)) {
                _storages.add(storage);

                // reverse Connection
                if (storage.getStore() != this) {
                    storage.setStore(this);
                }
                saveStore();
            }
        }
        public void removeStorage(Storage storage) {
            if (storage == null) return;
            if (_storages.contains(storage)) {
                _storages.remove(storage);

                // reverse connection
                if (storage.getStore() == this) {
                    storage.delete();
                }
                saveStore();
            }
        }

        // Getters and Setters
        public Address getLocationAddress() {
                return locationAddress;
        }

        public void setLocationAddress(Address locationAddress) {
                Objects.requireNonNull(locationAddress, "Address cannot be null.");
                this.locationAddress = locationAddress;
                saveStore();
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

        // updated for deleting all associations
        public void delete() {
            // Cascade delete for Composition
            for (ClosedFor event : new ArrayList<>(_closedForEvents)) {
                removeClosedFor(event);
            }
            // Remove associations
            for (Shift shift : new ArrayList<>(_shifts)) {
                removeShift(shift);
            }
            for (Contract contract : new ArrayList<>(_contracts)) {
                removeContract(contract);
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

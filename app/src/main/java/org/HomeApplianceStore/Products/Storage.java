package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Managment.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage implements Extent {

    private static ArrayList<Storage> storages = new ArrayList<Storage>();

        static {
                loadStorage();
        }

    private Store store;
    private Product product;

    private long inRepairAmount;
    private long usedStock;
    private long newStock;

    public Storage(Store store, Product product, long inRepairAmount, long usedStock, long newStock) {
        Objects.requireNonNull(store, "Storage must be associated with a Store");
        Objects.requireNonNull(product, "Storage must be associated with a Product");

        validateStockLevel(inRepairAmount);
        validateStockLevel(usedStock);
        validateStockLevel(newStock);

        this.store = store;
        this.product = product;
        this.inRepairAmount = inRepairAmount;
        this.usedStock = usedStock;
        this.newStock = newStock;

        store.addStorage(this);
        product.addStorage(this);

        addStorage(this);
        saveStorage();
    }
    public void deleteStorage() {
        store.removeStorage(this);
        product.removeStorageReverse(this);
        storages.remove(this);
        this.store = null;
        this.product = null;
        saveStorage();
    }
    private void validateStockLevel(long value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
    }


    private static void addStorage(Storage storage) {
        if (!storages.contains(storage)) {
            storages.add(storage);
        }
    }

    public long getInRepairAmount() {
        return inRepairAmount;
    }
    public void setInRepairAmount(long inRepairAmount) {
        validateStockLevel(inRepairAmount);
        this.inRepairAmount = inRepairAmount;
        saveStorage();
    }
    public long getUsedStock() {
        return usedStock;
    }
    public void setUsedStock(long usedStock) {
        validateStockLevel(usedStock);
        this.usedStock = usedStock;
        saveStorage();
    }
    public long getNewStock() {
        return newStock;
    }
    public void setNewStock(long newStock) {
        validateStockLevel(newStock);
        this.newStock = newStock;
        saveStorage();
    }

    public static void loadStorage() {
        storages = Extent.loadClassList("./org/HomeApplianceStore/Products/Storage.ser");
    }

    public static void saveStorage() {
        Extent.saveClassList("./org/HomeApplianceStore/Products/Storage.ser", storages);
    }

    public static List<Storage> getStorages() {
        return Extent.getImmutableClassList(storages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;
        Storage storage = (Storage) o;
        return inRepairAmount == storage.inRepairAmount &&
                usedStock == storage.usedStock &&
                newStock == storage.newStock &&
                Objects.equals(store, storage.store) &&
                Objects.equals(product, storage.product);
    }
    @Override
    public int hashCode() {
        return Objects.hash(inRepairAmount, usedStock, newStock);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        Objects.requireNonNull(store, "Storage must be associated with a Store");
        this.store = store;
        saveStorage();
    }

    public void delete() {
        store.removeStorage(this);
        product.removeStorageReverse(this);
        storages.remove(this);
        this.store = null;
        this.product = null;
        saveStorage();
    }
}

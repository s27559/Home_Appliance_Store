package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage implements Extent {

    private static ArrayList<Storage> storages = new ArrayList<Storage>();

        static {
                loadStorage();
        }

    private long inRepairAmmount;
    private long usedStock;
    private long newStock;

    public Storage(long inRepairAmmount, long usedStock, long newStock) {
        validateStockLevel(inRepairAmmount);
        validateStockLevel(usedStock);
        validateStockLevel(newStock);

        this.inRepairAmmount = inRepairAmmount;
        this.usedStock = usedStock;
        this.newStock = newStock;

        addStorage(this);
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

    public long getInRepairAmmount() {
        return inRepairAmmount;
    }
    public void setInRepairAmmount(long inRepairAmmount) {
        validateStockLevel(inRepairAmmount);
        this.inRepairAmmount = inRepairAmmount;
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
        return inRepairAmmount == storage.inRepairAmmount &&
                usedStock == storage.usedStock &&
                newStock == storage.newStock;
    }
    @Override
    public int hashCode() {
        return Objects.hash(inRepairAmmount, usedStock, newStock);
    }
}

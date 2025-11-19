package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.util.ArrayList;
import java.util.List;

public class Storage {

        private static ArrayList<Storage> storages = new ArrayList<Storage>();

        private long inRepairAmmount;
        private long usedStock;
        private long newStock;

        public Storage(long inRepairAmmount, long usedStock, long newStock) {
            this.inRepairAmmount = inRepairAmmount;
            this.usedStock = usedStock;
            this.newStock = newStock;

            addStorage(this);
        }

        private static void addStorage(Storage storage) {
            storages.add(storage);
        }

        public long getInRepairAmmount() {
                return inRepairAmmount;
        }
        public void setInRepairAmmount(long inRepairAmmount) {
                this.inRepairAmmount = inRepairAmmount;
        }
        public long getUsedStock() {
                return usedStock;
        }
        public void setUsedStock(long usedStock) {
                this.usedStock = usedStock;
        }
        public long getNewStock() {
                return newStock;
        }
        public void setNewStock(long newStock) {
                this.newStock = newStock;
        }

        public static void LoadStorage() {
            storages = Extent.loadClassList("./org/HomeApplianceStore/Products/Storage.ser");
        }

        public static void SaveStorage() {
            Extent.saveClassList("./org/HomeApplianceStore/Products/Storage.ser", storages);
        }

        public static List<Storage> getStorages() {
            return Extent.getImmutableClassList(storages);
        }
}

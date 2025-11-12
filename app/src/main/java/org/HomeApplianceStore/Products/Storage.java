package org.HomeApplianceStore.Products;

import java.util.ArrayList;

public class Storage {

        private ArrayList<Storage> storages = new ArrayList<Storage>();

        private long inRepairAmmount;
        private long usedStock;
        private long newStock;

        public Storage(long inRepairAmmount, long usedStock, long newStock) {
            this.inRepairAmmount = inRepairAmmount;
            this.usedStock = usedStock;
            this.newStock = newStock;

            storages.add(this);
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
}

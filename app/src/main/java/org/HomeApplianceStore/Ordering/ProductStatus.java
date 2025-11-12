package org.HomeApplianceStore.Ordering;

import jdk.jshell.Snippet;
import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductStatus implements Extent {
        private static ArrayList<ProductStatus> statuses= new ArrayList<ProductStatus>();

        private long ammountNew;
        private long ammountUsed;
        private boolean toBeMoved;
        private boolean toBeIntegrated;
        private BigDecimal differenceInPrice;

        public ProductStatus(long ammountNew,
                             long ammountUsed,
                             boolean toBeMoved,
                             boolean toBeIntegrated,
                             BigDecimal differenceInPrice) {

                this.setAmmountNew(ammountNew);
                this.setAmmountUsed(ammountUsed);
                this.setToBeMoved(toBeMoved);
                this.setToBeIntegrated(toBeIntegrated);
                this.setDifferenceInPrice(differenceInPrice);
                loadStatuses();

        }
        public static void ProductStatus(ProductStatus status) {
                statuses.add(status);
        }
        public long getAmmountNew() {
                return ammountNew;
        }

        public void setAmmountNew(long ammountNew) {
                this.ammountNew = ammountNew;
        }
        public long getAmmountUsed() {
                return ammountUsed;
        }
        public void setAmmountUsed(long ammountUsed) {
                this.ammountUsed = ammountUsed;
        }
        public boolean isToBeMoved() {
                return toBeMoved;
        }
        public void setToBeMoved(boolean toBeMoved) {
                this.toBeMoved = toBeMoved;
        }
        public boolean isToBeIntegrated() {
                return toBeIntegrated;
        }
        public void setToBeIntegrated(boolean toBeIntegrated) {
                this.toBeIntegrated = toBeIntegrated;
        }
        public BigDecimal getDifferenceInPrice() {
                return differenceInPrice;
        }
        public void setDifferenceInPrice(BigDecimal differenceInPrice) {
                this.differenceInPrice = differenceInPrice;
        }
        public static void loadStatuses(){
                statuses = Extent.loadClassList("./org/HomeApplianceStore/Ordering/ProductStatus.ser");
        }

        public static void saveStatuses(){
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/ProductStatus.ser", statuses);
        }

        public static List<ProductStatus> getStatuses() {
                return Extent.getImmutableClassList(statuses);
        }
}

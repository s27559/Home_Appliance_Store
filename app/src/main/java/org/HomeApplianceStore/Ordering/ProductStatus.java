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
                addProductStatus(this);

        }
        public static void addProductStatus(ProductStatus status) {
                if (status == null) {
                        throw new IllegalArgumentException("ProductStatus cannot be null");
                }
                statuses.add(status);
        }
        public long getAmmountNew() {
                return ammountNew;
        }

        public void setAmmountNew(long ammountNew) {
                if (ammountNew < 0) {
                        throw new IllegalArgumentException("ammountNew cannot be negative");
                }
                this.ammountNew = ammountNew;
        }
        public long getAmmountUsed() {
                return ammountUsed;
        }
        public void setAmmountUsed(long ammountUsed) {
                if (ammountUsed < 0) {
                        throw new IllegalArgumentException("ammountUsed cannot be negative");
                }
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
                if (differenceInPrice == null) {
                        throw new IllegalArgumentException("differenceInPrice cannot be null");
                }
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

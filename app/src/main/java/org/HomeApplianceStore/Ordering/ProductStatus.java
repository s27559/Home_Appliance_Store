package org.HomeApplianceStore.Ordering;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Ordering.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductStatus implements Extent {

        private static ArrayList<ProductStatus> statuses = new ArrayList<>();

        private long ammountNew;
        private long ammountUsed;
        private boolean toBeMoved;
        private boolean toBeIntegrated;
        private BigDecimal differenceInPrice;

        // Association to Order: each status entry belongs to one order
        private Order order;

        public ProductStatus(long ammountNew,
                             long ammountUsed,
                             boolean toBeMoved,
                             boolean toBeIntegrated,
                             BigDecimal differenceInPrice) {

                setAmmountNew(ammountNew);
                setAmmountUsed(ammountUsed);
                setToBeMoved(toBeMoved);
                setToBeIntegrated(toBeIntegrated);
                setDifferenceInPrice(differenceInPrice);
                addStatus(this);
        }

        private static void addStatus(ProductStatus status) {
                if (status == null) {
                        throw new IllegalArgumentException("status cannot be null");
                }
                statuses.add(status);
        }

        // ===== basic attributes =====

        public long getAmmountNew() {
                return ammountNew;
        }

        public void setAmmountNew(long ammountNew) {
                if (ammountNew < 0) {
                        throw new IllegalArgumentException("ammountNew must be >= 0");
                }
                this.ammountNew = ammountNew;
        }

        public long getAmmountUsed() {
                return ammountUsed;
        }

        public void setAmmountUsed(long ammountUsed) {
                if (ammountUsed < 0) {
                        throw new IllegalArgumentException("ammountUsed must be >= 0");
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

        // ================== Association with Order ==================

        public Order getOrder() {
                return order;
        }

        public void setOrder(Order order) {
                this.order = order;
        }

        // ===== extent handling =====

        public static void loadStatuses() {
                statuses = Extent.loadClassList("./org/HomeApplianceStore/Ordering/ProductStatus.ser");
        }

        public static void saveStatuses() {
                Extent.saveClassList("./org/HomeApplianceStore/Ordering/ProductStatus.ser", statuses);
        }

        public static List<ProductStatus> getStatuses() {
                return Extent.getImmutableClassList(statuses);
        }
}

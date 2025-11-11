package org.HomeApplianceStore.Ordering;

import java.math.BigDecimal;

public class ProductStatus {

        private long ammountNew;
        private long ammountUsed;
        private boolean toBeMoved;
        private boolean toBeIntegrated;
        private BigDecimal differenceInPrice;
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
}

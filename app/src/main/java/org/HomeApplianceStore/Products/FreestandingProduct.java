package org.HomeApplianceStore.Products;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FreestandingProduct {

        private static ArrayList<FreestandingProduct> freestandingProducts =new ArrayList<FreestandingProduct>();

        private BigDecimal moveCost;

        public FreestandingProduct(BigDecimal moveCost) {
            this.moveCost = moveCost;

            freestandingProducts.add(this);
        }

        public BigDecimal getMoveCost() {
                return moveCost;
        }

        public void setMoveCost(BigDecimal moveCost) {
                this.moveCost = moveCost;
        }
}

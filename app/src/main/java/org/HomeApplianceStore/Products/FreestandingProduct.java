package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FreestandingProduct {

        private static ArrayList<FreestandingProduct> freestandingProducts =new ArrayList<FreestandingProduct>();

        private BigDecimal moveCost;

        public FreestandingProduct(BigDecimal moveCost) {
            this.moveCost = moveCost;

            addFreestandingProduct(this);
        }

        private static void addFreestandingProduct(FreestandingProduct freestandingProduct) {
            freestandingProducts.add(freestandingProduct);
        }

        public BigDecimal getMoveCost() {
                return moveCost;
        }

        public void setMoveCost(BigDecimal moveCost) {
                this.moveCost = moveCost;
        }

        public static void loadFreestandingProducts() {
            freestandingProducts = Extent.loadClassList("./org/HomeApplianceStore/Products/FreestandingProduct.ser");
        }

        public static void saveFreestandingProducts() {
            Extent.saveClassList("./org/HomeApplianceStore/Products/FreestandingProduct.ser", freestandingProducts);
        }

        public static List<FreestandingProduct> getFreestandingProducts() {
            return Extent.getImmutableClassList(freestandingProducts);
        }
}

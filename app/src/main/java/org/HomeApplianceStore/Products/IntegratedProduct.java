package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IntegratedProduct {

        private static ArrayList<IntegratedProduct> integratedProducts = new ArrayList<IntegratedProduct>();

        private BigDecimal integrationCost;
        private boolean mustBeDone;

        public IntegratedProduct(String name, BigDecimal integrationCost, boolean mustBeDone) {
            this.integrationCost = integrationCost;
            this.mustBeDone = mustBeDone;

            addIntegratedProduct(this);
        }

        private static void addIntegratedProduct(IntegratedProduct integratedProduct){
            integratedProducts.add(integratedProduct);
        }

        public BigDecimal getIntegrationCost() {
                return integrationCost;
        }
        public void setIntegrationCost(BigDecimal integrationCost) {
                this.integrationCost = integrationCost;
        }
        public boolean isMustBeDone() {
                return mustBeDone;
        }
        public void setMustBeDone(boolean mustBeDone) {
                this.mustBeDone = mustBeDone;
        }

        public static void LoadIntegratedProducts(){
            integratedProducts = Extent.loadClassList("./org/HomeApplianceStore/Products/IntegratedProducts.ser");
        }

        public static void saveIntegratedProducts(){
            Extent.saveClassList("./org/HomeApplianceStore/Products/IntegratedProducts.ser", integratedProducts);
        }

        public static List<IntegratedProduct> getIntegratedProducts() {
            return Extent.getImmutableClassList(integratedProducts);
        }
}

package org.HomeApplianceStore.Products;

import java.math.BigDecimal;
import java.util.ArrayList;

public class IntegratedProduct {

        private static ArrayList<IntegratedProduct> integratedProducts = new ArrayList<IntegratedProduct>();

        private BigDecimal integrationCost;
        private boolean mustBeDone;

        public IntegratedProduct(String name, BigDecimal integrationCost, boolean mustBeDone) {
            this.integrationCost = integrationCost;
            this.mustBeDone = mustBeDone;

            integratedProducts.add(this);
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


}

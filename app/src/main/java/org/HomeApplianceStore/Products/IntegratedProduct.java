package org.HomeApplianceStore.Products;

import java.math.BigDecimal;

public class IntegratedProduct {

        private BigDecimal integrationCost;
        private boolean mustBeDone;
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


package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Products.Product;

public class CartProduct {

        private long ammountNew;
        private long ammountUsed;
        private Product product;

        public Product getProduct() {
                return product;
        }
        public void setProduct(Product product) {
                this.product = product;
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
}

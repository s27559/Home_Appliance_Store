
package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Products.Product;

public class CartProduct implements Extent{

        private static ArrayList<CartProduct> cartProducts = new ArrayList<>();
        
        private long ammountNew;
        private long ammountUsed;

        private Product product;
        private Customer customer;
        
        public CartProduct(long ammountNew, long ammountUsed) {
                this.ammountNew = ammountNew;
                this.ammountUsed = ammountUsed;

                cartProducts.add(this);
        }

        public static List<CartProduct> getCartProducts() {
                return Extent.getImmutableClassList(cartProducts);
        }

        public static void loadCartProducts(){
                cartProducts = Extent.loadClassList("./org/HomeApplianceStore/Actors/CartProduct.ser");
        }

        public static void saveCartProducts(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/CartProduct.ser", cartProducts);
        }

        private static void addCartProduct(CartProduct cartProduct) {
                cartProducts.add(cartProduct);
        }
        
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

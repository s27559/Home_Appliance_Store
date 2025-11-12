package org.HomeApplianceStore.Actors;

import java.util.ArrayList;

public class Customer {

        private ArrayList<CartProduct> cartProducts;

        public ArrayList<CartProduct> getCartProducts() {
                return cartProducts;
        }

        public void setCartProducts(ArrayList<CartProduct> cartProducts) {
                this.cartProducts = cartProducts;
        }
}

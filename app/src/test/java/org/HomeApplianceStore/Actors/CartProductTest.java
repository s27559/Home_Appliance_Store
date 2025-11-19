package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CartProductTest {

        @Test
        void testCorrectness(){
                long ammountNew = 10;
                long ammountUsed = 20;
                
                CartProduct cartProduct = new CartProduct(ammountNew, ammountUsed);

                assertTrue(ammountNew == cartProduct.getAmmountNew());
                assertTrue(ammountUsed == cartProduct.getAmmountUsed());
                assertTrue(CartProduct.getCartProducts().contains(cartProduct));
        }
}

package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomerPersonTest {

        @Test
        void testCorrectness(){
                long points = 100;
                
                CustomerPerson customerPerson = new CustomerPerson(points);

                assertTrue(points == customerPerson.getPoints());
                assertTrue(CustomerPerson.getCustomerPersons().contains(customerPerson));
        }
}

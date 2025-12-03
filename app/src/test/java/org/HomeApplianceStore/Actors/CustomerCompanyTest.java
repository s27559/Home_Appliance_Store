package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.HomeApplianceStore.Address;
import org.junit.jupiter.api.Test;

public class CustomerCompanyTest {

        @Test
        void testCorrectness(){
            CustomerCompany customerCompany = new CustomerCompany("nanme", "email@", new Address());

                assertTrue(CustomerCompany.getCustomerCompanies().contains(customerCompany));
        }
}

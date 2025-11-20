package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomerCompanyTest {

        @Test
        void testCorrectness(){                
                CustomerCompany customerCompany = new CustomerCompany();

                assertTrue(CustomerCompany.getCustomerCompanies().contains(customerCompany));
        }
}

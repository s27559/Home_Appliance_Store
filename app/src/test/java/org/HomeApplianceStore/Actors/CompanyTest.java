package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.HomeApplianceStore.Address;
import org.junit.jupiter.api.Test;

public class CompanyTest {

        @Test
        void testCorrectness(){
                String name = "name";
                String email = "name@surname";
                String phone = "1234567890";
                Address address = new Address();
                
                Company company = new Company(address, name, email, phone);

                assertTrue(name.equals(company.getName()));
                assertTrue(email.equals(company.getEmail()));
                assertTrue(phone.equals(company.getPhone()));
                assertTrue(address.equals(company.getAddress()));
                assertTrue(Company.getCompanies().contains(company));
        }
}

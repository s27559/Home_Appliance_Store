package org.HomeApplianceStore.Actors;

import org.HomeApplianceStore.Address;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPersonTest {

    private Person createSamplePerson() {
        return new Person(
                "John",
                "Customer",
                LocalDate.now().minusYears(25),
                null
        );
    }

    @Test
    void creatingValidCustomerPersonShouldSucceed() {

        CustomerPerson customer = new CustomerPerson(100L, "name", "email@", new Address());

        assertEquals(100L, customer.getPoints());
    }

    @Test
    void negativePointsShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new CustomerPerson(-100L, "name", "email@", new Address())
        );
    }

    @Test
    void nullPersonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new CustomerPerson(0L, "name", "", new Address())
        );
    }

    @Test
    void setPointsShouldValidate() {
        CustomerPerson customer = new CustomerPerson(100L, "name", "email@", new Address());

        assertThrows(IllegalArgumentException.class, () ->
                customer.setPoints(-5L)
        );

        customer.setPoints(10L);
        assertEquals(10L, customer.getPoints());
    }

    @Test
    void extentShouldUpdateAndBeImmutableForCustomerPerson() {
        int sizeBefore = CustomerPerson.getCustomerPersons().size();

        CustomerPerson customer = new CustomerPerson(5L, "name", "email@", new Address());

        int sizeAfterCreate = CustomerPerson.getCustomerPersons().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<CustomerPerson> immutable = CustomerPerson.getCustomerPersons();
        assertThrows(UnsupportedOperationException.class, () ->
                immutable.add(customer)
        );
    }
}

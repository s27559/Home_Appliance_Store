package org.HomeApplianceStore.Actors;

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
        Person person = createSamplePerson();

        CustomerPerson customer = new CustomerPerson(person, 100L);

        assertEquals(person, customer.getPerson());
        assertEquals(100L, customer.getPoints());
    }

    @Test
    void negativePointsShouldThrow() {
        Person person = createSamplePerson();

        assertThrows(IllegalArgumentException.class, () ->
                new CustomerPerson(person, -1L)
        );
    }

    @Test
    void nullPersonShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new CustomerPerson(null, 0L)
        );
    }

    @Test
    void setPointsShouldValidate() {
        CustomerPerson customer = new CustomerPerson(createSamplePerson(), 0L);

        assertThrows(IllegalArgumentException.class, () ->
                customer.setPoints(-5L)
        );

        customer.setPoints(10L);
        assertEquals(10L, customer.getPoints());
    }

    @Test
    void extentShouldUpdateAndBeImmutableForCustomerPerson() {
        int sizeBefore = CustomerPerson.getCustomerPersons().size();

        CustomerPerson customer = new CustomerPerson(createSamplePerson(), 5L);

        int sizeAfterCreate = CustomerPerson.getCustomerPersons().size();
        assertEquals(sizeBefore + 1, sizeAfterCreate);

        List<CustomerPerson> immutable = CustomerPerson.getCustomerPersons();
        assertThrows(UnsupportedOperationException.class, () ->
                immutable.add(customer)
        );
    }
}

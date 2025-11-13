package org.HomeApplianceStore.Actors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.HomeApplianceStore.Address;
import org.junit.jupiter.api.Test;

public class PersonTest {

        @Test
        void testCorrectness(){
                
                String name = "name";
                String surname = "surname";
                LocalDate dateOfBirth = LocalDate.now();
                Address address = new Address();
                Person person = new Person(name, surname, dateOfBirth, address);

                assertTrue(name.equals(person.getName()));
                assertTrue(surname.equals(person.getSurname()));
                assertTrue(dateOfBirth.equals(person.getDateOfBirth()));
                assertTrue(address.equals(person.getAddress()));
                assertTrue(Person.getPersons().contains(person));
        }
}

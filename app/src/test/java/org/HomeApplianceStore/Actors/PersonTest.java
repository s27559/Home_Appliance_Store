package org.HomeApplianceStore.Actors;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void creatingValidPersonShouldSucceed() {
        LocalDate dob = LocalDate.now().minusYears(20);

        Person person = new Person("John", "Doe", dob, null);

        assertEquals("John", person.getName());
        assertEquals("Doe", person.getSurname());
        assertEquals(dob, person.getDateOfBirth());
        assertNull(person.getAddress());
    }

    @Test
    void emptyNameShouldThrow() {
        LocalDate dob = LocalDate.now().minusYears(20);

        assertThrows(IllegalArgumentException.class, () ->
                new Person("   ", "Doe", dob, null)
        );
    }

    @Test
    void emptySurnameShouldThrow() {
        LocalDate dob = LocalDate.now().minusYears(20);

        assertThrows(IllegalArgumentException.class, () ->
                new Person("John", "   ", dob, null)
        );
    }

    @Test
    void nullDateOfBirthShouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("John", "Doe", null, null)
        );
    }

    @Test
    void futureDateOfBirthShouldThrow() {
        LocalDate future = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Person("John", "Doe", future, null)
        );
    }

    @Test
    void addressCanBeNullOptionalAttribute() {
        LocalDate dob = LocalDate.now().minusYears(30);
        Person person = new Person("Anna", "Smith", dob, null);

        assertNull(person.getAddress());
    }

    @Test
    void extentShouldUpdateAndBeImmutableForPerson() {
        int sizeBefore = Person.getPersons().size();

        Person p = new Person("Mark", "Twain", LocalDate.now().minusYears(40), null);

        int sizeAfter = Person.getPersons().size();
        assertEquals(sizeBefore + 1, sizeAfter);

        List<Person> immutable = Person.getPersons();
        assertThrows(UnsupportedOperationException.class, () ->
                immutable.add(p)
        );
    }
}

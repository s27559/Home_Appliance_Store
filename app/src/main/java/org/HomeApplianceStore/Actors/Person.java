package org.HomeApplianceStore.Actors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

public class Person implements Extent {

        private static ArrayList<Person> persons = new ArrayList<Person>();

        static {
                loadPersons();
        }

        private String name;
        private String middleName;
        private String surname;
        private LocalDate dateOfBirth;
        private Address address;

        public Person(String name, String surname, LocalDate dateOfBirth, Address address) {
                this.setName(name);
                this.setSurname(surname);
                this.setDateOfBirth(dateOfBirth);
                this.setAddress(address);
                addPerson(this);
        }

        public static void addPerson(Person person) {
                if (person == null) {
                        throw new IllegalArgumentException("Person cannot be null");
                }
                persons.add(person);
                savePersons();
        }

        public static void loadPersons() {
                persons = Extent.loadClassList("./org/HomeApplianceStore/Actors/Person.ser");
        }

        public static void savePersons() {
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Person.ser", persons);
        }

        public static List<Person> getPersons() {
                return Extent.getImmutableClassList(persons);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty");
                }
                this.name = name.trim();
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                if (surname == null || surname.trim().isEmpty()) {
                        throw new IllegalArgumentException("Surname cannot be empty");
                }
                this.surname = surname.trim();
        }

        public LocalDate getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
                if (dateOfBirth == null) {
                        throw new IllegalArgumentException("Date of birth cannot be null");
                }
                if (dateOfBirth.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException("Date of birth cannot be in the future");
                }
                this.dateOfBirth = dateOfBirth;
        }

        public Address getAddress() {
                return address;
        }
        public void setAddress(Address address) {
                this.address = address;
        }

        public long getAge(){
                return dateOfBirth.until(LocalDate.now()).getYears();
        }

        public Optional<String> getMiddleName() {
                return Optional.of(middleName);
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }
        
}

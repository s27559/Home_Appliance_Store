package org.HomeApplianceStore.Actors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

public class Person implements Extent{
        private static ArrayList<Person> persons = new ArrayList<>();

        private String name;
        private String surname;
        private LocalDate dateOfBirth;
        private Address address;
        // age

        public Person(String name, String surname, LocalDate dateOfBirth, Address address) {
                this.name = name;
                this.surname = surname;
                this.dateOfBirth = dateOfBirth;
                this.address = address;
                addPerson(this);
        }

        public static List<Person> getPersons() {
                return Extent.getImmutableClassList(persons);
        }

        public static void loadPersons(){
                persons = Extent.loadClassList("./org/HomeApplianceStore/Actors/Person.ser");
        }

        public static void savePersons(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Person.ser", persons);
        }

        private static void addPerson(Person person) {
                persons.add(person);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
        public String getSurname() {
                return surname;
        }
        public void setSurname(String surname) {
                this.surname = surname;
        }
        public LocalDate getDateOfBirth() {
                return dateOfBirth;
        }
        public void setDateOfBirth(LocalDate dateOfBirth) {
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
        
}

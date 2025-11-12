package org.HomeApplianceStore.Actors;

import java.time.LocalDate;

import org.HomeApplianceStore.Address;

public class Person {

        private String name;
        private String surname;
        private LocalDate dateOfBirth;
        private Address address;
        // age
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
        
}

package org.HomeApplianceStore;

import java.util.Optional;

public class Address {

        private String country;
        private String reagion;
        private String street;
        private String streetNum;
        private String apartmentNum; // Optional
        private String postalCode;
        public String getCountry() {
                return country;
        }
        public void setCountry(String country) {
                this.country = country;
        }
        public String getReagion() {
                return reagion;
        }
        public void setReagion(String reagion) {
                this.reagion = reagion;
        }
        public String getStreet() {
                return street;
        }
        public void setStreet(String street) {
                this.street = street;
        }
        public String getStreetNum() {
                return streetNum;
        }
        public void setStreetNum(String streetNum) {
                this.streetNum = streetNum;
        }
        public String getApartmentNum() {
                return apartmentNum;
        }
        public void setApartmentNum(String apartmentNum) {
                this.apartmentNum = apartmentNum;
        }
        public String getPostalCode() {
                return postalCode;
        }
        public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
        }
}

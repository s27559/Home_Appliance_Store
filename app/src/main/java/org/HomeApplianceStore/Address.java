package org.HomeApplianceStore;

import java.io.Serializable;
import java.util.Optional;

public class Address implements Serializable{

        private String country;
        private String region;
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
        public String getRegion() {
                return region;
        }
        public void setRegion(String region) {
                this.region = region;
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
        public Optional<String> getApartmentNum() {
                return Optional.of(apartmentNum);
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

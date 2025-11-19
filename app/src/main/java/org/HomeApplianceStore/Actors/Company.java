package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

public class Company implements Extent{
        private static ArrayList<Company> companies = new ArrayList<>();

        private String name;
        private String email;
        private String phone;
        private Address address;

        public Company(Address address, String name, String email, String phone) {
                this.address = address;
                this.name = name;
                this.email = email;
                this.phone = phone;
                addCompany(this);
        }

        private static void addCompany(Company company) {
                companies.add(company);
        }

        public static void loadCompanies(){
                companies = Extent.loadClassList("./org/HomeApplianceStore/Actors/Company.ser");
        }

        public static void saveCompanies() {
                Extent.saveClassList("./org/HomeApplianceStore/Actors/Company.ser", companies);
        }

        public static List<Company> getCompanies(){
                return Extent.getImmutableClassList(companies);
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }
        public String getPhone() {
                return phone;
        }
        public void setPhone(String phone) {
                this.phone = phone;
        }
        public Address getAddress() {
                return address;
        }
        public void setAddress(Address address) {
                address = address;
        }
}

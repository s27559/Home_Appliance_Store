package org.HomeApplianceStore.Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.HomeApplianceStore.Address;
import org.HomeApplianceStore.Extent;

public class Company implements Extent{

    private static final String FILE_LOCATION = "./org/HomeApplianceStore/Actors/Company.ser";

    private static ArrayList<Company> companies = new ArrayList<>();

    private String name;
    private String email;
    private String phone;
    private Address address;

    public Company(Address address, String name, String email, String phone) {
        validateName(name);
        validateEmail(email);
        validatePhone(phone);
        Objects.requireNonNull(address, "Company address cannot be null.");

        this.address = address;
        this.name = name;
        this.email = email;
        this.phone = phone;

        addCompany(this);
        saveCompanies();
    }

    private void validateName(String name) {
        Objects.requireNonNull(name, "Company name cannot be null.");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty.");
        }
    }

    private void validateEmail(String email) {
        Objects.requireNonNull(email, "Company email cannot be null.");
        if (!email.contains("@") || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Company email is invalid.");
        }
    }

    private void validatePhone(String phone) {
        if (phone != null && phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty if provided.");
        }
    }

    private static void addCompany(Company company) {
        if (!companies.contains(company)) {
            companies.add(company);
        }
    }

    public static void loadCompanies(){
        List<Company> loaded = Extent.loadClassList(FILE_LOCATION);
        companies = (loaded == null) ? new ArrayList<>() : new ArrayList<>(loaded);
    }

    public static void saveCompanies() {
        Extent.saveClassList(FILE_LOCATION, companies);
    }

    public static List<Company> getCompanies(){
        return Extent.getImmutableClassList(companies);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        validateName(name);
        this.name = name;
        saveCompanies();
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
        saveCompanies();
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
        saveCompanies();
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        Objects.requireNonNull(address, "Company address cannot be null.");
        this.address = address;
        saveCompanies();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(email, company.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
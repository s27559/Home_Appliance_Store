package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.HomeApplianceStore.Extent;
import org.HomeApplianceStore.Managment.Validation; // Validation sınıfını kullanıyoruz

public class CustomerCompany extends Customer implements Extent {

    private static final String FILE_LOCATION = "./org/HomeApplianceStore/Actors/CustomerCompany.ser";
    private static ArrayList<CustomerCompany> customerCompanies = new ArrayList<>();
    private static BigDecimal bulkOrderDiscount;

    private Company company;

    public CustomerCompany(Company company) {
        super();
        Objects.requireNonNull(company, "Company cannot be null.");
        this.company = company;
        addCustomerCompany(this);
        saveCustomerCompanies();
    }

    public static List<CustomerCompany> getCustomerCompanies() {
        return Extent.getImmutableClassList(customerCompanies);
    }

    public static void loadCustomerCompanies() {
        customerCompanies = Extent.loadClassList(FILE_LOCATION);
    }

    public static void saveCustomerCompanies() {
        Extent.saveClassList(FILE_LOCATION, customerCompanies);
    }

    private static void addCustomerCompany(CustomerCompany customerCompany) {
        if (!customerCompanies.contains(customerCompany)) {
            customerCompanies.add(customerCompany);
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        Objects.requireNonNull(company, "Company cannot be null.");
        this.company = company;
        saveCustomerCompanies();
    }

    public static BigDecimal getBulkOrderDiscount() {
        return bulkOrderDiscount;
    }

    public static void setBulkOrderDiscount(BigDecimal bulkOrderDiscount) {
        if (bulkOrderDiscount != null) {
            Validation.validateBigDecimal(bulkOrderDiscount, "Bulk Order Discount");
        }
        CustomerCompany.bulkOrderDiscount = bulkOrderDiscount;
    }

    public void delete() {
        customerCompanies.remove(this);
        saveCustomerCompanies();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCompany)) return false;
        CustomerCompany that = (CustomerCompany) o;
        return Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company);
    }
}
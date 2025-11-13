package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.HomeApplianceStore.Extent;

public class CustomerCompany extends Customer implements Extent{
        private static ArrayList<CustomerCompany> customerCompanies = new ArrayList<>();

        private static BigDecimal bulkOrderDiscount;
        private Company company;

        public CustomerCompany() {
                super();
                addCustomerCompany(this);
        }

        public static List<CustomerCompany> getCustomerCompanies() {
                return Extent.getImmutableClassList(customerCompanies);
        }

        public static void loadCustomerCompanies(){
                customerCompanies = Extent.loadClassList("./org/HomeApplianceStore/Actors/CustomerCompany.ser");
        }

        public static void saveCustomerCompanies(){
                Extent.saveClassList("./org/HomeApplianceStore/Actors/CustomerCompany.ser", customerCompanies);
        }

        private static void addCustomerCompany(CustomerCompany customerCompany) {
                customerCompanies.add(customerCompany);
        }

        public Company getCompany() {
                return company;
        }

        public void setCompany(Company company) {
                this.company = company;
        }

        public static BigDecimal getBulkOrderDiscount() {
                return bulkOrderDiscount;
        }

        public static void setBulkOrderDiscount(BigDecimal bulkOrderDiscount) {
                CustomerCompany.bulkOrderDiscount = bulkOrderDiscount;
        }
}

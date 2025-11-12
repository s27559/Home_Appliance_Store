package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;

public class CustomerCompany extends Customer {

        private static BigDecimal bulkOrderDiscount;
        private Company company;

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

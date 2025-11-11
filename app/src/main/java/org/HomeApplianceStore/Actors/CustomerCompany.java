package org.HomeApplianceStore.Actors;

import java.math.BigDecimal;

public class CustomerCompany {

        private static BigDecimal bulkOrderDiscount;

        public static BigDecimal getBulkOrderDiscount() {
                return bulkOrderDiscount;
        }

        public static void setBulkOrderDiscount(BigDecimal bulkOrderDiscount) {
                CustomerCompany.bulkOrderDiscount = bulkOrderDiscount;
        }
}

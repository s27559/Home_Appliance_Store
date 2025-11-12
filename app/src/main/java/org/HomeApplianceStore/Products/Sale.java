package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {

        private static ArrayList<Sale> sales = new ArrayList<Sale>();

        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal ammount;
        // periodDays

        public Sale(String name, LocalDate startDate, LocalDate endDate, BigDecimal ammount) {
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
            this.ammount = ammount;

            addSale(this);
        }

        private static void addSale(Sale sale) {
            sales.add(sale);
        }

        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public LocalDate getStartDate() {
                return startDate;
        }
        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }
        public LocalDate getEndDate() {
                return endDate;
        }
        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }
        public BigDecimal getAmmount() {
                return ammount;
        }
        public void setAmmount(BigDecimal ammount) {
                this.ammount = ammount;
        }

        public long getPeriodDays() {
                return startDate.until(endDate).getDays();
        }

        public static void LoadSales() {
            sales = Extent.loadClassList("./org/HomeApplianceStore/Products/Sale.ser");
        }

        public static void SaveSales() {
            Extent.saveClassList("./org/HomeApplianceStore/Products/Sale.ser", sales);
        }

        public static List<Sale> getSales() {
            return Extent.getImmutableClassList(sales);
        }
}

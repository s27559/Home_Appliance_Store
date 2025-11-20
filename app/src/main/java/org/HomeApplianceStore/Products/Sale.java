package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale implements Extent {

    private static ArrayList<Sale> sales = new ArrayList<Sale>();

        static {
                loadSales();
        }

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;

    public Sale(String name, LocalDate startDate, LocalDate endDate, BigDecimal amount) {
        validateName(name);
        validateDates(startDate,  endDate);
        validateAmount(amount);

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount.setScale(2,  RoundingMode.HALF_UP);

        addSale(this);
        saveSales();
    }
    private void validateName(String name) {
        Objects.requireNonNull(name, "Sale name cannot be null.");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Sale name cannot be empty.");
        }
    }
    private void validateDates(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate, "Start date cannot be null.");
        Objects.requireNonNull(endDate, "End date cannot be null.");
        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException("End date cannot be before start date.");
    }
    private void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Sale amount cannot be null.");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sale amount must be a positive value.");
        }
    }

    private static void addSale(Sale sale) {
        if(!sales.contains(sale)) {
            sales.add(sale);
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        validateName(name);
        this.name = name;
        saveSales();
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        Objects.requireNonNull(startDate, "Start date cannot be null.");
        if(this.endDate != null && this.endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.startDate = startDate;
        saveSales();
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        Objects.requireNonNull(endDate, "End date cannot be null.");
        if(this.startDate != null && endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("End date cannot be before end date.");
        }
        this.endDate = endDate;
        saveSales();
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount;
        saveSales();
    }

    public long getPeriodDays() {
        return startDate.until(endDate).getDays();
    }

    public static void loadSales() {
        sales = Extent.loadClassList("./org/HomeApplianceStore/Products/Sale.ser");
    }

    public static void saveSales() {
        Extent.saveClassList("./org/HomeApplianceStore/Products/Sale.ser", sales);
    }

    public static List<Sale> getSales() {
        return Extent.getImmutableClassList(sales);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(name, sale.name) &&
                Objects.equals(startDate, sale.startDate) &&
                Objects.equals(endDate, sale.endDate) &&
                Objects.equals(amount, sale.amount);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, amount);
    }
}

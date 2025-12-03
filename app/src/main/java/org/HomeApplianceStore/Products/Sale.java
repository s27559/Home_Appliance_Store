package org.HomeApplianceStore.Products;

import org.HomeApplianceStore.Extent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class Sale implements Extent {

    private static ArrayList<Sale> sales = new ArrayList<Sale>();

        static {
                loadSales();
        }

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;

    private Set<Product> products = new HashSet<>();

    public Sale(String name, LocalDate startDate, LocalDate endDate, BigDecimal amount, Set<Product> initialProducts) {
        validateName(name);
        validateDates(startDate,  endDate);
        validateAmount(amount);
        validateProducts(initialProducts);

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount.setScale(2,  RoundingMode.HALF_UP);

        for (Product p : products) {
            addProduct(p);
        }

        addSale(this);
        saveSales();
    }
    private void validateProducts(Set<Product> products) {
        Objects.requireNonNull(products, "Sale must be associated with at least one product (1..*).");
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Sale must be associated with at least one product (1..*).");
        }
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
    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }
    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product to add cannot be null.");
        if (products.contains(product)) {
            return;
        }

        products.add(product);
        product.addSaleReverse(this);
        saveSales();
    }
    public void removeProduct(Product product) {
        Objects.requireNonNull(product, "Product to remove cannot be null.");

        if (products.remove(product)) {
            product.removeSaleReverse(this);
            saveSales();
        }
    }
    void addProductReverse(Product product) {
        products.add(product);
        saveSales();
    }

    void removeProductReverse(Product product) {
        products.remove(product);
        saveSales();
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

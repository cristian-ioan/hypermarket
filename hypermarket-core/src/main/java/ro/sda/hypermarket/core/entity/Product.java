package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products", schema = "hypermarket")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "supplier_price", length = 20, nullable = false)
    private BigDecimal supplierPrice;

    @Column(name = "vending_price", length = 20, nullable = false)
    private BigDecimal vendingPrice;

    @Column(name = "stock", length = 20)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private Set<SaleProduct> saleProductProduct;

    public Set<SaleProduct> getSaleProductProduct() {
        return saleProductProduct;
    }

    public void setSaleProductProduct(Set<SaleProduct> saleProductProduct) {
        this.saleProductProduct = saleProductProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public BigDecimal getVendingPrice() {
        return vendingPrice;
    }

    public void setVendingPrice(BigDecimal vendingPrice) {
        this.vendingPrice = vendingPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getSupplierPrice(), product.getSupplierPrice()) &&
                Objects.equals(getVendingPrice(), product.getVendingPrice()) &&
                Objects.equals(getStock(), product.getStock()) &&
                Objects.equals(getSupplier(), product.getSupplier()) &&
                Objects.equals(getProductCategory(), product.getProductCategory()) &&
                Objects.equals(getSaleProductProduct(), product.getSaleProductProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSupplierPrice(), getVendingPrice(), getStock(), getSupplier(), getProductCategory(), getSaleProductProduct());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplierPrice=" + supplierPrice +
                ", vendingPrice=" + vendingPrice +
                ", stock=" + stock +
                ", supplier=" + supplier +
                ", productCategory=" + productCategory +
                ", saleProductProduct=" + saleProductProduct +
                '}';
    }

}

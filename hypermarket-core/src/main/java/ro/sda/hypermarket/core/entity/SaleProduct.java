package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sale_products", schema = "hypermarket")
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleProduct)) return false;
        SaleProduct that = (SaleProduct) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getQuantity(), that.getQuantity()) &&
                Objects.equals(getSale(), that.getSale()) &&
                Objects.equals(getProduct(), that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuantity(), getSale(), getProduct());
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", sale=" + sale +
                ", product=" + product +
                '}';
    }

}

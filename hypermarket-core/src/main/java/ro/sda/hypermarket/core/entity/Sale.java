package ro.sda.hypermarket.core.entity;

import ro.sda.hypermarket.core.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sales", schema = "hypermarket")
public class Sale extends BaseEntity {

    @Column( name = "invoice_number", length = 20, nullable = false)
    private Long number;

    @Column(name = "sale_date", length = 8, nullable = false)
    private LocalDate saleDate;

    @Column(name = "employee_id", length = 10,nullable = false)
    private String employeeId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "sale")
    private Set<SaleProduct> saleProductsSale;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(getId(), sale.getId()) &&
                Objects.equals(getNumber(), sale.getNumber()) &&
                Objects.equals(getSaleDate(), sale.getSaleDate()) &&
                Objects.equals(getEmployeeId(), sale.getEmployeeId()) &&
                Objects.equals(getClient(), sale.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getSaleDate(), getEmployeeId(), getClient());
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + getId() +
                ", number=" + number +
                ", saleDate=" + saleDate +
                ", employeeId='" + employeeId + '\'' +
                ", client=" + client +
                ", saleProductsSale=" + saleProductsSale +
                '}';
    }

}

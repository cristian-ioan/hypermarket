package ro.sda.hypermarket.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "sale_products", schema = "hypermarket")
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}

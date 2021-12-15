package ru.haazad.java.architectures.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}

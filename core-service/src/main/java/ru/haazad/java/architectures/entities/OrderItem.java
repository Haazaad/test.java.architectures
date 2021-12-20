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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final OrderItem item = new OrderItem();

        public Builder product(Product product) {
            item.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            item.quantity = quantity;
            return this;
        }

        public Builder productPrice(BigDecimal productPrice) {
            item.productPrice = productPrice;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            item.totalPrice = totalPrice;
            return this;
        }

        public OrderItem build() {
            return item;
        }
    }
}

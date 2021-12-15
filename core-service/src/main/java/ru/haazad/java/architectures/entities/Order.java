package ru.haazad.java.architectures.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime createdDate;

    @Column(name = "price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}

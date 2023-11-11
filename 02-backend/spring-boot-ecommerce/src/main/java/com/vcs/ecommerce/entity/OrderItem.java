package com.vcs.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imageUrl;

    private BigDecimal unitPrice;

    private int quantity;

    private long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

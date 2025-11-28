package com.example.demo.entites;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data  // Génère getters, setters, toString, equals, hashCode
@Entity
@Table(name = "customer_orders")  // ⚠️ CHANGER LE NOM DE LA TABLE

public class Order {
    @Id @GeneratedValue
    private Long id;

    private Long productId;
    private Integer quantity;
    private BigDecimal totalPrice;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters setters
}

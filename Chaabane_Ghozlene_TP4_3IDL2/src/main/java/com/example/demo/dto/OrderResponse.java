package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OrderResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalPrice;
    public OrderResponse(Long id, Long productId, Integer quantity, BigDecimal totalPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}

package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data  // Génère getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
     public Long id;
    public String name;
    public Integer stock;
    public BigDecimal price;
}

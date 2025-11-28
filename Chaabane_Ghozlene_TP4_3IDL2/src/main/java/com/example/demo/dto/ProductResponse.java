package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private ProductRequest product;
    private String message;
    public boolean isAvailable() {
        return product != null;
    }
}

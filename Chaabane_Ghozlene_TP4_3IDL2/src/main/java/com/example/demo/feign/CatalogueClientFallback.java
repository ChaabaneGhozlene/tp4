package com.example.demo.feign;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ProductResponse;
@Component

public class CatalogueClientFallback implements CatalogueClient {
     @Override
    public ProductResponse getProduct(Long id) {
        return new ProductResponse(
                null, 
                "Catalogue Service unavailable"
        );
    }
}

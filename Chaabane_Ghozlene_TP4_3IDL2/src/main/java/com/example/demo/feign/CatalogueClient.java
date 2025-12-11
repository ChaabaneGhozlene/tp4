package com.example.demo.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProductResponse;

import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(
        name = "CATALOGUE-SERVICE",
        fallback = CatalogueClientFallback.class
)
public interface CatalogueClient {
     @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);
}

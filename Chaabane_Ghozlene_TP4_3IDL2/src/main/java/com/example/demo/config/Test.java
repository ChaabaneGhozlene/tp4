package com.example.demo.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductResponse;
import com.example.demo.feign.CatalogueClient;
@RestController
@RequestMapping("/test")

public class Test {
     private CatalogueClient catalogueClient = null;

    public Test(CatalogueClient catalogueClient) {
        this.catalogueClient = catalogueClient;
    }

    @GetMapping("/product/{id}")
    public ProductResponse testProduct(@PathVariable Long id) {
        ProductResponse product = catalogueClient.getProduct(id);
        System.out.println("Produit reçu : " + product);
        return product;
    }
}

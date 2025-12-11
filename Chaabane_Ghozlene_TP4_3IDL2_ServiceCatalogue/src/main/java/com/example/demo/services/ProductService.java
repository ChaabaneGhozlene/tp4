package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entites.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    // Retourne tous les produits
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Retourne un produit par ID
    public ProductResponse getProductById(Long id) {
        Product p = productRepository.findById(id)
.orElseThrow(() -> new ProductNotFoundException(id));        return mapToDto(p);
    }

    // Vérifie si stock suffisant, lance exception sinon
    public void checkStock(Long id, int quantity) {
        Product p = productRepository.findById(id)
.orElseThrow(() -> new ProductNotFoundException(id));
        if (p.getStock() < quantity) {
            throw new RuntimeException("Stock insuffisant pour le produit " + id);
        }
    }
        // Créer un produit
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    // Modifier un produit
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
.orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        // Pas besoin de save car @Transactional + JPA gère le merge
        return mapToDto(product);
    }

    // Supprimer un produit
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    // Mapper Product → ProductResponse
    private ProductResponse mapToDto(Product product) {
    // Crée d'abord un ProductRequest à partir de Product
    ProductRequest productRequest = new ProductRequest(
        product.getId(),
        product.getName(),
        product.getStock(),
        product.getPrice()
    );

    // Ensuite envelopper dans ProductResponse
    return new ProductResponse(productRequest, "success");
}

}

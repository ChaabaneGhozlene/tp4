package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.services.ProductNotFoundException;
import com.example.demo.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

    // GET /products → liste de tous les produits
    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAllProducts();
    }

    // GET /products/{id} → produit spécifique
    // ?qty=3 → vérification stock si nécessaire
@GetMapping("/{id}")
public ResponseEntity<ProductResponse> getOne(@PathVariable Long id) {
    try {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    } catch (ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ProductResponse(null, ex.getMessage()));
    }
}



     // POST /products → créer un produit
    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    // PUT /products/{id} → modifier un produit
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    // DELETE /products/{id} → supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}

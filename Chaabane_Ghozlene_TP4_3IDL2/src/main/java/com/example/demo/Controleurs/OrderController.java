package com.example.demo.Controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.services.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

   
    private OrderService orderService;
     @Autowired
     public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
@GetMapping("/test")
public String test(HttpServletRequest request) {
    return "Réponse de l'instance sur le port " + request.getLocalPort();
}
    // Création d'une commande
    @PostMapping
public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
    OrderResponse response = orderService.createOrder(request);
    return ResponseEntity.ok(response);
}

@GetMapping("/{id}")
public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
    try {
        OrderResponse order = orderService.findById(id);
        return ResponseEntity.ok(order);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
    // Endpoint santé
    @GetMapping("/health")
    public String health() {
        return "MSX Order Service UP";
    }
}

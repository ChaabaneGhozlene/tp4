package com.example.demo.services;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entites.Order;
import com.example.demo.feign.CatalogueClient;
import com.example.demo.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
private final OrderRepository orderRepository;
    private final CatalogueClient catalogueClient;
    private final RabbitTemplate rabbitTemplate;

    @CircuitBreaker(name = "catalogueBreaker", fallbackMethod = "fallbackProduct")
    @Retry(name = "catalogueRetry")
    @TimeLimiter(name = "catalogueTimeout")
    public CompletableFuture<ProductResponse> getProduct(Long productId) {
        return CompletableFuture.supplyAsync(() -> catalogueClient.getProduct(productId));
    }

    public CompletableFuture<ProductResponse> fallbackProduct(Long productId, Throwable ex) {
        return CompletableFuture.completedFuture(
            new ProductResponse(null, "Catalogue Service unavailable")
        );
    }

    @Override
 public OrderResponse createOrder(OrderRequest request) {
        ProductResponse productResponse = getProduct(request.getProductId()).join();
        ProductRequest product = productResponse.getProduct();

        if (product == null) {
            throw new RuntimeException(productResponse.getMessage()); // message du fallback ou produit absent
        }

        // Vérification du stock
        if (request.getQuantity() > product.getStock()) {
            throw new RuntimeException("Quantité demandée supérieure au stock disponible");
        }

        Order order = new Order();
        order.setProductId(product.getId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));

        Order savedOrder = orderRepository.save(order);

        // envoyer notification RabbitMQ
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, "Nouvelle commande: " + savedOrder.getId());

        return new OrderResponse(
            savedOrder.getId(),
            savedOrder.getProductId(),
            savedOrder.getQuantity(),
            savedOrder.getTotalPrice()
        );
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        return new OrderResponse(order.getId(), order.getProductId(),
                order.getQuantity(), order.getTotalPrice());
    }
}

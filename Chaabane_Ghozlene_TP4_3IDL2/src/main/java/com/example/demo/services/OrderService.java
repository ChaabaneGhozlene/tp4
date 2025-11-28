package com.example.demo.services;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entites.Order;

public interface OrderService {
     OrderResponse createOrder(OrderRequest request);  // retourne DTO
    OrderResponse findById(Long id);                 // méthode attendue par le controller

}


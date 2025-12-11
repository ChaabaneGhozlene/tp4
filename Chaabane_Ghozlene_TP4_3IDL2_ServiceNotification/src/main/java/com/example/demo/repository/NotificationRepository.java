package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entites.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
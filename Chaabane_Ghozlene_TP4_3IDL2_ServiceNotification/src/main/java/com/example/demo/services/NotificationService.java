package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entites.Notification;
import com.example.demo.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor

public class NotificationService {
private final NotificationRepository repository;

    public Notification sendNotification(String recipient, String message) {
        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        // Simulation d'envoi d'email
        System.out.println("Envoi d'email à " + recipient + ": " + message);

        return repository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
}

package com.example.demo.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entites.Notification;
import com.example.demo.services.NotificationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class NotificationController {

private final NotificationService  notificationService;
    // Endpoint POST pour tester depuis Postman
    @PostMapping("/send")
    public Notification sendNotification(
            @RequestParam String recipient,
            @RequestParam String message) {
        return notificationService.sendNotification(recipient, message);
    }

    // Endpoint GET pour récupérer toutes les notifications
    @GetMapping("/logs")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

}

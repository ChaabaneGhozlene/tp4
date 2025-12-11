package com.example.demo.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.NotificationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class NotificationListener {
     private final NotificationService service;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
public void listen(NotificationDTO message) {
    service.sendNotification(message.getRecipient(), message.getMessage());
}
}

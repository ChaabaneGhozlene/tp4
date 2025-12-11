package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.repository.NotificationRepository;
@SpringBootApplication
   // ← IMPORTANT
@EnableDiscoveryClient

public class NotificationServiceApplication {
       public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
      @Bean
    CommandLineRunner testMongo(NotificationRepository repo) {
        return args -> System.out.println("Nombre de notifications: " + repo.count());
    }
}

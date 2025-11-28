package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

@EnableDiscoveryClient

@EnableFeignClients  // ⚠️ TRÈS IMPORTANT
public class MsxOrderApplication {
       public static void main(String[] args) {
        SpringApplication.run(MsxOrderApplication.class, args);
    }
}

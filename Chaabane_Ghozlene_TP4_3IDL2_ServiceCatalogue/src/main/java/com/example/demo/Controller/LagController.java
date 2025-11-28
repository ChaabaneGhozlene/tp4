package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LagController {
    @GetMapping("/simulate-lag")
    public String simulateLag() throws InterruptedException {
        Thread.sleep(4000); // 4 secondes de délai pour tester timeout
        return "Lag simulated";
    }
}

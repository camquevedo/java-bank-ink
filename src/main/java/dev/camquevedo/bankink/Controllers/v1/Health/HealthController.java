package dev.camquevedo.bankink.Controllers.v1.Health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/health")
    public String checkAPI() {
        return "<h1>The API is working ok!</h1>";
    }

    @GetMapping("/health2")
    public String checkAPI2() {
        return "<h1>The API is working ok!</h1>";
    }
}

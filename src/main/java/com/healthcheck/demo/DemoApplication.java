package com.healthcheck.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Custom health check endpoint implemented as a standard Spring MVC RestController.
	 * * This achieves the health check functionality without relying on Spring Boot Actuator.
	 * The endpoint will be available at: http://localhost:8080/health
	 */
	@RestController
	public static class CustomHealthController {

		// This method handles GET requests to /health
		@GetMapping("/health")
		public Map<String, String> checkHealth() {

			// --- Custom Health Logic ---

			// In a real production application, you would add logic here to check:
			// 1. Database connectivity
			// 2. External service dependencies
			// 3. Application operational status (e.g., memory usage)

			boolean isDatabaseOk = true; // Placeholder for DB check
			boolean isExternalApiOk = true; // Placeholder for external API check

			if (isDatabaseOk && isExternalApiOk) {
				// Service is considered healthy
				return Collections.singletonMap("status", "UP");
			} else {
				// Service is considered unhealthy (you might throw an exception
				// or return a different HTTP status code in a true failure scenario)
				return Collections.singletonMap("status", "DEGRADED");
			}
		}
	}
}


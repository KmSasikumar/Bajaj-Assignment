package com.bajajfinserv.webhookassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebhookAssignmentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebhookAssignmentApplication.class, args);
        
        // Trigger the webhook generation on startup
        WebhookService webhookService = context.getBean(WebhookService.class);
        webhookService.executeAssignmentFlow();
    }
}
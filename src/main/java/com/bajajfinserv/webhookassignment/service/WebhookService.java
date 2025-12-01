package com.bajajfinserv.webhookassignment.service;

import com.bajajfinserv.webhookassignment.model.WebhookRequest;
import com.bajajfinserv.webhookassignment.model.WebhookResponse;
import com.bajajfinserv.webhookassignment.model.SqlSubmissionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebhookService {

    private final WebClient webClient;
    private final SqlSolutionService sqlSolutionService;

    public WebhookService(WebClient.Builder webClientBuilder, SqlSolutionService sqlSolutionService) {
        this.webClient = webClientBuilder.build();
        this.sqlSolutionService = sqlSolutionService;
    }

    public void executeAssignmentFlow() {
        try {
            log.info("Starting webhook assignment flow...");
            
            // Step 1: Generate webhook
            WebhookResponse webhookResponse = generateWebhook();
            
            if (webhookResponse != null) {
                log.info("Webhook generated successfully. Webhook URL: {}", webhookResponse.getWebhook());
                
                // Step 2: Get SQL solution based on registration number
                String sqlQuery = sqlSolutionService.getSqlSolution("REG12347");
                log.info("Generated SQL solution: {}", sqlQuery);
                
                // Step 3: Submit solution
                submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlQuery);
            } else {
                log.error("Failed to generate webhook");
            }
        } catch (Exception e) {
            log.error("Error in assignment flow: {}", e.getMessage(), e);
        }
    }

    private WebhookResponse generateWebhook() {
        try {
            String generateWebhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
            
            WebhookRequest request = new WebhookRequest();
            request.setName("John Doe");
            request.setRegNo("REG12347");
            request.setEmail("john@example.com");

            Mono<WebhookResponse> response = webClient.post()
                    .uri(generateWebhookUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(WebhookResponse.class);

            return response.block();
        } catch (Exception e) {
            log.error("Error generating webhook: {}", e.getMessage());
            return null;
        }
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            SqlSubmissionRequest submissionRequest = new SqlSubmissionRequest();
            submissionRequest.setFinalQuery(sqlQuery);

            Mono<String> response = webClient.post()
                    .uri(webhookUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .bodyValue(submissionRequest)
                    .retrieve()
                    .bodyToMono(String.class);

            String result = response.block();
            log.info("Solution submitted successfully. Response: {}", result);
        } catch (Exception e) {
            log.error("Error submitting solution: {}", e.getMessage());
        }
    }
}
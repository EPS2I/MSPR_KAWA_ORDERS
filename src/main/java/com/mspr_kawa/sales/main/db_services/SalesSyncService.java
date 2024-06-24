package com.mspr_kawa.sales.main.db_services;

import com.mspr_kawa.sales.main.model.Sale;
import com.mspr_kawa.sales.main.security.KeycloakConfigCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SalesSyncService {

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${app.config-url.api-db}")
    private String apiDbUrl;

    private final RestTemplate restTemplate;

    private final KeycloakConfigCustom keycloakConfigCustom;

    public SalesSyncService(RestTemplate restTemplate, KeycloakConfigCustom keycloakConfigCustom) {
        this.restTemplate = restTemplate;
        this.keycloakConfigCustom = keycloakConfigCustom;
    }

    public List<Sale> fetchSalesFromMainDb() throws IOException {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/customers";
        String accessToken = keycloakConfigCustom.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Sale[]> response = restTemplate.exchange(apiBaseUrl, HttpMethod.GET, entity, Sale[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException("Failed to get data from API BDD: " + response.getStatusCode());
        }
    }

    public void syncSalesToMainDb(List<Sale> customers) {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/customers";
        for (Sale customer : customers) {
            restTemplate.postForEntity(apiBaseUrl, customer, Sale.class);
        }
    }
}

package br.com.sucaslilva.hubSpot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;

@Service
public class TokenService {
    @Value("${hubSpot.clientID}")
    private String clientId;

    @Value("${hubSpot.scope}")
    private String scope;

    @Value("${hubSpot.redirectUri}")
    private String redirectUri;

    @Value("${hubSpot.clientSecret}")
    private String clientSecret;

    @Value("${hubSpot.urlApi.token}")
    private String urlAPI;

    private Bucket bucket;

    @PostConstruct
    public void inicializaBucket() {
        Refill refill = Refill.intervally(100, Duration.ofSeconds(10));
        Bandwidth limit = Bandwidth.classic(100, refill);
        bucket = Bucket4j.builder().addLimit(limit).build();
    }

    public String buscaToken(String code){
        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Limite de chamadas à API do HubSpot atingido. Tente novamente em instantes.");
        }
        try {
            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("grant_type", "authorization_code");
            formData.add("client_id", clientId);
            formData.add("client_secret", clientSecret);
            formData.add("redirect_uri", redirectUri);
            formData.add("code", code);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(urlAPI + "token", entity, Map.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

package br.com.sucaslilva.hubSpot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContatoService {

    @Value("${hubSpot.urlApi.Contato}")
    private String urlAPI;


    public String criaContato(String token, String dadosContato){
        ResponseEntity<String> response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            System.out.println("JSON: " + dadosContato);
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(dadosContato, headers);
            response= restTemplate.postForEntity(urlAPI, entity, String.class);

        } catch (Exception e) {
            System.err.println("Erro ao chamar API: " + e.getMessage());
        }
        return (response.getBody());
    }

    public String buscarContatos (String token){
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(urlAPI, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar contatos", e);
        }
    }

}

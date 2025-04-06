package br.com.sucaslilva.hubSpot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlAuth")
@RequiredArgsConstructor
public class AutenticacaoController {

    @Value("${hubSpot.clientID}")
    private String clientId;

    @Value("${hubSpot.scope}")
    private String scope;

    @Value("${hubSpot.redirectUri}")
    private String redirectUri;

    private static final String URL_AUTH = "https://app.hubspot.com/oauth/authorize";

    @GetMapping
    public ResponseEntity<String> gerarUrlAutorizacao() {
        String url = URL_AUTH + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=" + scope;
        return ResponseEntity.ok(url);
    }
}

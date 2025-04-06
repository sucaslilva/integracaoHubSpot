package br.com.sucaslilva.hubSpot.controller;

import br.com.sucaslilva.hubSpot.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginHubSpot")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> gerarToken(@RequestParam("code") String code){
        String token = tokenService.buscaToken(code);
        return ResponseEntity.ok("Acesso ao token:" + token);
    }
}

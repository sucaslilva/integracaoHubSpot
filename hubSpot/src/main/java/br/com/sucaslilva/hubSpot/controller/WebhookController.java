package br.com.sucaslilva.hubSpot.controller;

import br.com.sucaslilva.hubSpot.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    @PostMapping("/contato")
    private ResponseEntity<String> eventoContato(@RequestBody String requestBody, HttpServletRequest request,
                                                 @RequestHeader("X-HubSpot-Signature-v3") String signature,
                                                 @RequestHeader("X-HubSpot-Request-Timestamp") Timestamp horaRequest) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (!webhookService.validaAssinatura(signature,uri, requestBody, method ))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Assinatura inválida");

        System.out.println("Um novo contato foi cadastrado: " + requestBody);
        return ResponseEntity.ok().build();
    }
}

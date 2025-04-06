package br.com.sucaslilva.hubSpot.controller;

import br.com.sucaslilva.hubSpot.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<String> geraContato(@RequestHeader("Authorization") String token, @RequestBody String dadosContato) {
        String tokenS = token.replace("Bearer ", "");
        String response =contatoService.criaContato(tokenS, dadosContato);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping()
    public ResponseEntity<String> contatos(@RequestHeader("Authorization") String token){
        String tokenS = token.replace("Bearer ", "");
        String response = contatoService.buscarContatos(tokenS);
        return ResponseEntity.ok(response) ;
    }
}

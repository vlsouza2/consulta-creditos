package com.desafio.api.controller;

import com.desafio.api.domain.model.Credito;
import com.desafio.api.domain.service.CreditoService;
import com.desafio.api.domain.service.KafkaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService service;

    @Autowired
    private KafkaPublisherService kafkaPublisher;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List> findByNumeroNfse(@PathVariable String numeroNfse) {

        List<Credito> credito = service.findByNumeroNfse(numeroNfse);

        if (!credito.isEmpty()) {
            kafkaPublisher.publicarConsulta("Consulta realizada para numeroNfse nº " + numeroNfse);
            return ResponseEntity.ok(credito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("numeroNfse não encontrado."));
        }
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<?> findByNumeroCredito(@PathVariable String numeroCredito) {
        Credito credito = service.findByNumeroCredito(numeroCredito);

        if (credito != null) {
            kafkaPublisher.publicarConsulta("Consulta realizada para numeroCredito nº " + numeroCredito);
            return ResponseEntity.ok(credito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensagem", "numeroCredito não encontrado."));
        }
    }

}
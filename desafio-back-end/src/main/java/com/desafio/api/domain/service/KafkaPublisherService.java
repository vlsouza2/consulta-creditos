package com.desafio.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {

    private static final String TOPICO = "consultas-topico";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publicarConsulta(String mensagem) {
        kafkaTemplate.send(TOPICO, mensagem);
    }
}
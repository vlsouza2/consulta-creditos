package com.desafio.api.domain.service;

import com.desafio.api.domain.model.Credito;
import com.desafio.api.domain.repository.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {
    @Autowired
    private CreditoRepository repository;

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse);
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito);
    }

}

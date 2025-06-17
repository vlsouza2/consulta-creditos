package com.desafio.api.domain.repository;

import com.desafio.api.domain.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String numeroNfse);
    Credito findByNumeroCredito(String numeroCredito);
}

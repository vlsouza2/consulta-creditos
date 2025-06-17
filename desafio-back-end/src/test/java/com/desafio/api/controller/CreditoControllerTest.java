package com.desafio.api.controller;

import com.desafio.api.domain.model.Credito;
import com.desafio.api.domain.service.CreditoService;
import com.desafio.api.domain.service.KafkaPublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
class CreditoControllerTest {

    @Mock
    private CreditoService creditoService;

    @Mock
    private KafkaPublisherService kafkaPublisher;

    @InjectMocks
    private CreditoController creditoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByNumeroNfse_WhenFound_ShouldReturnOkWithCreditos() {

        String nfse = "12345";
        Credito credito = new Credito();
        credito.setNumeroNfse(nfse);
        List<Credito> creditos = Arrays.asList(credito);

        when(creditoService.findByNumeroNfse(nfse)).thenReturn(creditos);


        ResponseEntity<List> response = creditoController.findByNumeroNfse(nfse);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditos, response.getBody());
        verify(kafkaPublisher).publicarConsulta("Consulta realizada para numeroNfse nº " + nfse);
    }

    @Test
    void findByNumeroNfse_WhenNotFound_ShouldReturnNotFound() {

        String nfse = "99999";
        when(creditoService.findByNumeroNfse(nfse)).thenReturn(Collections.emptyList());


        ResponseEntity<List> response = creditoController.findByNumeroNfse(nfse);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("numeroNfse não encontrado.", response.getBody().get(0));
        verify(kafkaPublisher, never()).publicarConsulta(anyString());
    }


    @Test
    void findByNumeroNfse_WithNullInput_ShouldHandleGracefully() {

        when(creditoService.findByNumeroNfse(null)).thenReturn(Collections.emptyList());


        ResponseEntity<List> response = creditoController.findByNumeroNfse(null);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("numeroNfse não encontrado.", response.getBody().get(0));
    }

    @Test
    void findByNumeroCredito_QuandoExistir_DeveRetornarOkComCredito() {

        String numeroCredito = "CREDITO-123";
        Credito creditoMock = new Credito();
        creditoMock.setNumeroCredito(numeroCredito);

        when(creditoService.findByNumeroCredito(numeroCredito))
                .thenReturn(creditoMock);


        ResponseEntity<?> response = creditoController.findByNumeroCredito(numeroCredito);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditoMock, response.getBody());
        verify(kafkaPublisher).publicarConsulta("Consulta realizada para numeroCredito nº " + numeroCredito);
    }

    @Test
    void findByNumeroCredito_QuandoNaoExistir_DeveRetornarNotFound() {
        // Arrange
        String numeroCredito = "INEXISTENTE";
        when(creditoService.findByNumeroCredito(numeroCredito))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = creditoController.findByNumeroCredito(numeroCredito);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("numeroCredito não encontrado.",
                ((Map<?, ?>) response.getBody()).get("mensagem"));
        verify(kafkaPublisher, never()).publicarConsulta(anyString());
    }

    @Test
    void findByNumeroCredito_QuandoChamadoSemNumero_DeveRetornarBadRequest() {
        // Act & Assert
        assertThrows(MethodArgumentNotValidException.class, () -> {
            creditoController.findByNumeroCredito(""); // Ou null se permitido
        });
    }
}
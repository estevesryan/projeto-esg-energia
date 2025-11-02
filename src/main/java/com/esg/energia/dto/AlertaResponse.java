package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaResponse {
    private Long id;
    private Long unidadeId;
    private String unidadeNome;
    private String tipo;
    private String mensagem;
    private BigDecimal consumoRegistradoKwh;
    private BigDecimal limiteKwh;
    private Boolean lido;
    private LocalDateTime createdAt;
}

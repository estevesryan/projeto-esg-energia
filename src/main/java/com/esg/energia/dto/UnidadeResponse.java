package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeResponse {
    private Long id;
    private String nome;
    private String localizacao;
    private BigDecimal limiteConsumoDiarioKwh;
    private Boolean ativo;
    private LocalDateTime createdAt;
}

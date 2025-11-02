package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndicadoresResponse {
    private Long unidadeId;
    private String unidadeNome;
    private BigDecimal consumoDiarioKwh;
    private BigDecimal consumoMensalKwh;
    private BigDecimal limiteConsumoDiarioKwh;
    private BigDecimal percentualConsumoDiario;
    private Boolean limiteDiarioExcedido;
    private Integer totalAlertasNaoLidos;
}

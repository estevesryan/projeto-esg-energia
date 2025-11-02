package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraResponse {
    private Long id;
    private Long unidadeId;
    private String unidadeNome;
    private LocalDateTime timestamp;
    private BigDecimal consumoKwh;
    private String sensorId;
    private LocalDateTime createdAt;
}

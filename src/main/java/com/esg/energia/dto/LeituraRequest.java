package com.esg.energia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraRequest {
    
    @NotNull(message = "ID da unidade é obrigatório")
    private Long unidadeId;
    
    @NotNull(message = "Timestamp é obrigatório")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    
    @NotNull(message = "Consumo em kWh é obrigatório")
    @DecimalMin(value = "0.0", message = "Consumo não pode ser negativo")
    private BigDecimal consumoKwh;
    
    private String sensorId;
}

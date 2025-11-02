package com.esg.energia.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;
    
    @NotNull(message = "Limite de consumo diário é obrigatório")
    @DecimalMin(value = "0.01", message = "Limite deve ser maior que zero")
    private BigDecimal limiteConsumoDiarioKwh;
}

package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoResponse {
    private Long id;
    private Long unidadeId;
    private String unidadeNome;
    private String descricao;
    private String tipoAcao;
    private LocalDateTime dataHoraAgendada;
    private String status;
    private String observacao;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

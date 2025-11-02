package com.esg.energia.service;

import com.esg.energia.dto.IndicadoresResponse;
import com.esg.energia.entity.Unidade;
import com.esg.energia.repository.AlertaRepository;
import com.esg.energia.repository.LeituraRepository;
import com.esg.energia.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Service
public class IndicadorService {
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    @Autowired
    private LeituraRepository leituraRepository;
    
    @Autowired
    private AlertaRepository alertaRepository;
    
    public IndicadoresResponse obterIndicadores(Long unidadeId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        
        // Consumo diário
        LocalDateTime inicioDia = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime fimDia = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        BigDecimal consumoDiario = leituraRepository.somarConsumoPorPeriodo(unidadeId, inicioDia, fimDia);
        if (consumoDiario == null) consumoDiario = BigDecimal.ZERO;
        
        // Consumo mensal
        YearMonth mesAtual = YearMonth.now();
        LocalDateTime inicioMes = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fimMes = mesAtual.atEndOfMonth().atTime(LocalTime.MAX);
        BigDecimal consumoMensal = leituraRepository.somarConsumoPorPeriodo(unidadeId, inicioMes, fimMes);
        if (consumoMensal == null) consumoMensal = BigDecimal.ZERO;
        
        // Percentual do consumo diário
        BigDecimal percentual = BigDecimal.ZERO;
        if (unidade.getLimiteConsumoDiarioKwh().compareTo(BigDecimal.ZERO) > 0) {
            percentual = consumoDiario
                    .divide(unidade.getLimiteConsumoDiarioKwh(), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
        
        // Limite excedido?
        boolean limiteExcedido = consumoDiario.compareTo(unidade.getLimiteConsumoDiarioKwh()) > 0;
        
        // Total de alertas não lidos para esta unidade
        int totalAlertasNaoLidos = alertaRepository.findByUnidadeIdAndLidoFalse(unidadeId).size();
        
        return new IndicadoresResponse(
                unidade.getId(),
                unidade.getNome(),
                consumoDiario,
                consumoMensal,
                unidade.getLimiteConsumoDiarioKwh(),
                percentual,
                limiteExcedido,
                totalAlertasNaoLidos
        );
    }
}

package com.esg.energia.service;

import com.esg.energia.dto.LeituraRequest;
import com.esg.energia.dto.LeituraResponse;
import com.esg.energia.entity.Alerta;
import com.esg.energia.entity.Leitura;
import com.esg.energia.entity.Unidade;
import com.esg.energia.repository.AlertaRepository;
import com.esg.energia.repository.LeituraRepository;
import com.esg.energia.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeituraService {
    
    @Autowired
    private LeituraRepository leituraRepository;
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    @Autowired
    private AlertaRepository alertaRepository;
    
    public List<LeituraResponse> listarPorUnidade(Long unidadeId) {
        return leituraRepository.findByUnidadeIdOrderByTimestampDesc(unidadeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<LeituraResponse> listarPorPeriodo(Long unidadeId, LocalDateTime inicio, LocalDateTime fim) {
        return leituraRepository.findByUnidadeIdAndTimestampBetween(unidadeId, inicio, fim).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public LeituraResponse registrar(LeituraRequest request) {
        Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        
        Leitura leitura = new Leitura();
        leitura.setUnidade(unidade);
        leitura.setTimestamp(request.getTimestamp());
        leitura.setConsumoKwh(request.getConsumoKwh());
        leitura.setSensorId(request.getSensorId());
        
        leitura = leituraRepository.save(leitura);
        
        // Verificar se precisa gerar alerta
        verificarEGerarAlerta(unidade);
        
        return toResponse(leitura);
    }
    
    private void verificarEGerarAlerta(Unidade unidade) {
        LocalDateTime inicioDia = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime fimDia = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        
        BigDecimal consumoDiario = leituraRepository.somarConsumoPorPeriodo(
                unidade.getId(), inicioDia, fimDia);
        
        if (consumoDiario != null && 
            consumoDiario.compareTo(unidade.getLimiteConsumoDiarioKwh()) > 0) {
            
            Alerta alerta = new Alerta();
            alerta.setUnidade(unidade);
            alerta.setTipo("CONSUMO_EXCEDIDO");
            alerta.setMensagem(String.format(
                    "Consumo diário de %.2f kWh ultrapassou o limite de %.2f kWh",
                    consumoDiario, unidade.getLimiteConsumoDiarioKwh()));
            alerta.setConsumoRegistradoKwh(consumoDiario);
            alerta.setLimiteKwh(unidade.getLimiteConsumoDiarioKwh());
            alerta.setLido(false);
            
            alertaRepository.save(alerta);
        }
    }
    
    private LeituraResponse toResponse(Leitura leitura) {
        return new LeituraResponse(
                leitura.getId(),
                leitura.getUnidade().getId(),
                leitura.getUnidade().getNome(),
                leitura.getTimestamp(),
                leitura.getConsumoKwh(),
                leitura.getSensorId(),
                leitura.getCreatedAt()
        );
    }
}

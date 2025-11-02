package com.esg.energia.service;

import com.esg.energia.dto.AlertaResponse;
import com.esg.energia.entity.Alerta;
import com.esg.energia.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaService {
    
    @Autowired
    private AlertaRepository alertaRepository;
    
    public List<AlertaResponse> listarTodos() {
        return alertaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<AlertaResponse> listarNaoLidos() {
        return alertaRepository.findByLidoFalseOrderByCreatedAtDesc().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<AlertaResponse> listarPorUnidade(Long unidadeId) {
        return alertaRepository.findByUnidadeIdOrderByCreatedAtDesc(unidadeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public long contarNaoLidos() {
        return alertaRepository.countByLidoFalse();
    }
    
    @Transactional
    public void marcarComoLido(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
        alerta.setLido(true);
        alertaRepository.save(alerta);
    }
    
    @Transactional
    public void marcarTodosComoLidos() {
        List<Alerta> alertasNaoLidos = alertaRepository.findByLidoFalseOrderByCreatedAtDesc();
        alertasNaoLidos.forEach(alerta -> alerta.setLido(true));
        alertaRepository.saveAll(alertasNaoLidos);
    }
    
    private AlertaResponse toResponse(Alerta alerta) {
        return new AlertaResponse(
                alerta.getId(),
                alerta.getUnidade().getId(),
                alerta.getUnidade().getNome(),
                alerta.getTipo(),
                alerta.getMensagem(),
                alerta.getConsumoRegistradoKwh(),
                alerta.getLimiteKwh(),
                alerta.getLido(),
                alerta.getCreatedAt()
        );
    }
}

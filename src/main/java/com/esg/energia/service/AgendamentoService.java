package com.esg.energia.service;

import com.esg.energia.dto.AgendamentoRequest;
import com.esg.energia.dto.AgendamentoResponse;
import com.esg.energia.entity.Agendamento;
import com.esg.energia.entity.Unidade;
import com.esg.energia.repository.AgendamentoRepository;
import com.esg.energia.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    public List<AgendamentoResponse> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<AgendamentoResponse> listarPorUnidade(Long unidadeId) {
        return agendamentoRepository.findByUnidadeIdOrderByDataHoraAgendadaDesc(unidadeId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<AgendamentoResponse> listarPorStatus(String status) {
        Agendamento.StatusAgendamento statusEnum = Agendamento.StatusAgendamento.valueOf(status.toUpperCase());
        return agendamentoRepository.findByStatus(statusEnum).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public AgendamentoResponse buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return toResponse(agendamento);
    }
    
    @Transactional
    public AgendamentoResponse criar(AgendamentoRequest request) {
        Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        
        Agendamento agendamento = new Agendamento();
        agendamento.setUnidade(unidade);
        agendamento.setDescricao(request.getDescricao());
        agendamento.setTipoAcao(request.getTipoAcao());
        agendamento.setDataHoraAgendada(request.getDataHoraAgendada());
        agendamento.setObservacao(request.getObservacao());
        agendamento.setStatus(Agendamento.StatusAgendamento.PENDENTE);
        
        agendamento = agendamentoRepository.save(agendamento);
        return toResponse(agendamento);
    }
    
    @Transactional
    public AgendamentoResponse atualizar(Long id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        
        agendamento.setDescricao(request.getDescricao());
        agendamento.setTipoAcao(request.getTipoAcao());
        agendamento.setDataHoraAgendada(request.getDataHoraAgendada());
        agendamento.setObservacao(request.getObservacao());
        
        agendamento = agendamentoRepository.save(agendamento);
        return toResponse(agendamento);
    }
    
    @Transactional
    public void atualizarStatus(Long id, String status) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        
        Agendamento.StatusAgendamento statusEnum = Agendamento.StatusAgendamento.valueOf(status.toUpperCase());
        agendamento.setStatus(statusEnum);
        agendamentoRepository.save(agendamento);
    }
    
    @Transactional
    public void cancelar(Long id) {
        atualizarStatus(id, "CANCELADO");
    }
    
    private AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getUnidade().getId(),
                agendamento.getUnidade().getNome(),
                agendamento.getDescricao(),
                agendamento.getTipoAcao(),
                agendamento.getDataHoraAgendada(),
                agendamento.getStatus().name(),
                agendamento.getObservacao(),
                agendamento.getCreatedAt(),
                agendamento.getUpdatedAt()
        );
    }
}

package com.esg.energia.service;

import com.esg.energia.dto.UnidadeRequest;
import com.esg.energia.dto.UnidadeResponse;
import com.esg.energia.entity.Unidade;
import com.esg.energia.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeService {
    
    @Autowired
    private UnidadeRepository unidadeRepository;
    
    public List<UnidadeResponse> listarTodas() {
        return unidadeRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<UnidadeResponse> listarAtivas() {
        return unidadeRepository.findByAtivoTrue().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public UnidadeResponse buscarPorId(Long id) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        return toResponse(unidade);
    }
    
    @Transactional
    public UnidadeResponse criar(UnidadeRequest request) {
        Unidade unidade = new Unidade();
        unidade.setNome(request.getNome());
        unidade.setLocalizacao(request.getLocalizacao());
        unidade.setLimiteConsumoDiarioKwh(request.getLimiteConsumoDiarioKwh());
        unidade.setAtivo(true);
        
        unidade = unidadeRepository.save(unidade);
        return toResponse(unidade);
    }
    
    @Transactional
    public UnidadeResponse atualizar(Long id, UnidadeRequest request) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        
        unidade.setNome(request.getNome());
        unidade.setLocalizacao(request.getLocalizacao());
        unidade.setLimiteConsumoDiarioKwh(request.getLimiteConsumoDiarioKwh());
        
        unidade = unidadeRepository.save(unidade);
        return toResponse(unidade);
    }
    
    @Transactional
    public void desativar(Long id) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        unidade.setAtivo(false);
        unidadeRepository.save(unidade);
    }
    
    private UnidadeResponse toResponse(Unidade unidade) {
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome(),
                unidade.getLocalizacao(),
                unidade.getLimiteConsumoDiarioKwh(),
                unidade.getAtivo(),
                unidade.getCreatedAt()
        );
    }
}

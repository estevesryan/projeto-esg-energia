package com.esg.energia.repository;

import com.esg.energia.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    List<Agendamento> findByUnidadeIdOrderByDataHoraAgendadaDesc(Long unidadeId);
    
    List<Agendamento> findByStatus(Agendamento.StatusAgendamento status);
    
    List<Agendamento> findByStatusAndDataHoraAgendadaBefore(
        Agendamento.StatusAgendamento status, 
        LocalDateTime dataHora
    );
    
    List<Agendamento> findByDataHoraAgendadaBetween(LocalDateTime inicio, LocalDateTime fim);
}

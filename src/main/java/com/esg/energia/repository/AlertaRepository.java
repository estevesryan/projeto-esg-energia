package com.esg.energia.repository;

import com.esg.energia.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByUnidadeIdOrderByCreatedAtDesc(Long unidadeId);
    List<Alerta> findByLidoFalseOrderByCreatedAtDesc();
    List<Alerta> findByUnidadeIdAndLidoFalse(Long unidadeId);
    long countByLidoFalse();
}

package com.esg.energia.repository;

import com.esg.energia.entity.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    
    List<Leitura> findByUnidadeIdOrderByTimestampDesc(Long unidadeId);
    
    List<Leitura> findByUnidadeIdAndTimestampBetween(Long unidadeId, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT SUM(l.consumoKwh) FROM Leitura l WHERE l.unidade.id = :unidadeId AND l.timestamp BETWEEN :inicio AND :fim")
    BigDecimal somarConsumoPorPeriodo(@Param("unidadeId") Long unidadeId, 
                                      @Param("inicio") LocalDateTime inicio, 
                                      @Param("fim") LocalDateTime fim);
    
    @Query("SELECT l FROM Leitura l WHERE l.unidade.id = :unidadeId ORDER BY l.timestamp DESC")
    List<Leitura> findUltimasLeituras(@Param("unidadeId") Long unidadeId);
}

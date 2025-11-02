package com.esg.energia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALERTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Alerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIDADE_ID", nullable = false)
    private Unidade unidade;
    
    @Column(nullable = false, length = 50)
    private String tipo;
    
    @Column(nullable = false, length = 500)
    private String mensagem;
    
    @Column(name = "CONSUMO_REGISTRADO_KWH", nullable = false, precision = 10, scale = 2)
    private BigDecimal consumoRegistradoKwh;
    
    @Column(name = "LIMITE_KWH", nullable = false, precision = 10, scale = 2)
    private BigDecimal limiteKwh;
    
    @Column(nullable = false)
    private Boolean lido = false;
    
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

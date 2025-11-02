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
@Table(name = "LEITURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Leitura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIDADE_ID", nullable = false)
    private Unidade unidade;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(name = "CONSUMO_KWH", nullable = false, precision = 10, scale = 2)
    private BigDecimal consumoKwh;
    
    @Column(name = "SENSOR_ID", length = 50)
    private String sensorId;
    
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

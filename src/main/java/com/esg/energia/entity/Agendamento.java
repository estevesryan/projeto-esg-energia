package com.esg.energia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "AGENDAMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIDADE_ID", nullable = false)
    private Unidade unidade;
    
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @Column(name = "TIPO_ACAO", nullable = false, length = 50)
    private String tipoAcao;
    
    @Column(name = "DATA_HORA_AGENDADA", nullable = false)
    private LocalDateTime dataHoraAgendada;
    
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.PENDENTE;
    
    @Column(length = 500)
    private String observacao;
    
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    
    public enum StatusAgendamento {
        PENDENTE, EXECUTADO, CANCELADO
    }
}

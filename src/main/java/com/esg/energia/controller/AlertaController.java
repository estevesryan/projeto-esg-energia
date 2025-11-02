package com.esg.energia.controller;

import com.esg.energia.dto.AlertaResponse;
import com.esg.energia.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@SecurityRequirement(name = "bearer-jwt")
@Tag(name = "Alertas", description = "Gerenciamento de alertas de consumo excedido")
public class AlertaController {
    
    @Autowired
    private AlertaService alertaService;
    
    @GetMapping
    @Operation(summary = "Listar todos os alertas")
    public ResponseEntity<List<AlertaResponse>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }
    
    @GetMapping("/nao-lidos")
    @Operation(summary = "Listar alertas não lidos")
    public ResponseEntity<List<AlertaResponse>> listarNaoLidos() {
        return ResponseEntity.ok(alertaService.listarNaoLidos());
    }
    
    @GetMapping("/unidade/{unidadeId}")
    @Operation(summary = "Listar alertas por unidade")
    public ResponseEntity<List<AlertaResponse>> listarPorUnidade(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(alertaService.listarPorUnidade(unidadeId));
    }
    
    @GetMapping("/contador")
    @Operation(summary = "Contar alertas não lidos")
    public ResponseEntity<Long> contarNaoLidos() {
        return ResponseEntity.ok(alertaService.contarNaoLidos());
    }
    
    @PatchMapping("/{id}/marcar-lido")
    @Operation(summary = "Marcar alerta como lido")
    public ResponseEntity<Void> marcarComoLido(@PathVariable Long id) {
        alertaService.marcarComoLido(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/marcar-todos-lidos")
    @Operation(summary = "Marcar todos os alertas como lidos")
    public ResponseEntity<Void> marcarTodosComoLidos() {
        alertaService.marcarTodosComoLidos();
        return ResponseEntity.noContent().build();
    }
}

package com.esg.energia.controller;

import com.esg.energia.dto.AgendamentoRequest;
import com.esg.energia.dto.AgendamentoResponse;
import com.esg.energia.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@SecurityRequirement(name = "bearer-jwt")
@Tag(name = "Agendamentos", description = "Gerenciamento de agendamentos de ações de redução energética")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService agendamentoService;
    
    @GetMapping
    @Operation(summary = "Listar todos os agendamentos")
    public ResponseEntity<List<AgendamentoResponse>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }
    
    @GetMapping("/unidade/{unidadeId}")
    @Operation(summary = "Listar agendamentos por unidade")
    public ResponseEntity<List<AgendamentoResponse>> listarPorUnidade(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(agendamentoService.listarPorUnidade(unidadeId));
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Listar agendamentos por status", description = "Status: PENDENTE, EXECUTADO, CANCELADO")
    public ResponseEntity<List<AgendamentoResponse>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(agendamentoService.listarPorStatus(status));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar novo agendamento", description = "Requer role ADMIN")
    public ResponseEntity<AgendamentoResponse> criar(@Valid @RequestBody AgendamentoRequest request) {
        AgendamentoResponse response = agendamentoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar agendamento", description = "Requer role ADMIN")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Long id, 
                                                          @Valid @RequestBody AgendamentoRequest request) {
        return ResponseEntity.ok(agendamentoService.atualizar(id, request));
    }
    
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar status do agendamento", description = "Requer role ADMIN")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        agendamentoService.atualizarStatus(id, status);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cancelar agendamento", description = "Requer role ADMIN")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        agendamentoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}

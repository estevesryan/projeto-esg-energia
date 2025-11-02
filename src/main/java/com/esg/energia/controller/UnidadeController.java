package com.esg.energia.controller;

import com.esg.energia.dto.UnidadeRequest;
import com.esg.energia.dto.UnidadeResponse;
import com.esg.energia.service.UnidadeService;
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
@RequestMapping("/api/unidades")
@SecurityRequirement(name = "bearer-jwt")
@Tag(name = "Unidades", description = "Gerenciamento de unidades de monitoramento")
public class UnidadeController {
    
    @Autowired
    private UnidadeService unidadeService;
    
    @GetMapping
    @Operation(summary = "Listar todas as unidades")
    public ResponseEntity<List<UnidadeResponse>> listarTodas() {
        return ResponseEntity.ok(unidadeService.listarTodas());
    }
    
    @GetMapping("/ativas")
    @Operation(summary = "Listar unidades ativas")
    public ResponseEntity<List<UnidadeResponse>> listarAtivas() {
        return ResponseEntity.ok(unidadeService.listarAtivas());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar unidade por ID")
    public ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(unidadeService.buscarPorId(id));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar nova unidade", description = "Requer role ADMIN")
    public ResponseEntity<UnidadeResponse> criar(@Valid @RequestBody UnidadeRequest request) {
        UnidadeResponse response = unidadeService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar unidade", description = "Requer role ADMIN")
    public ResponseEntity<UnidadeResponse> atualizar(@PathVariable Long id, 
                                                      @Valid @RequestBody UnidadeRequest request) {
        return ResponseEntity.ok(unidadeService.atualizar(id, request));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Desativar unidade", description = "Requer role ADMIN")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        unidadeService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}

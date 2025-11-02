package com.esg.energia.controller;

import com.esg.energia.dto.LeituraRequest;
import com.esg.energia.dto.LeituraResponse;
import com.esg.energia.service.LeituraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leituras")
@Tag(name = "Leituras", description = "Registro e consulta de leituras de consumo energético")
public class LeituraController {
    
    @Autowired
    private LeituraService leituraService;
    
    @GetMapping
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "Listar leituras por unidade")
    public ResponseEntity<List<LeituraResponse>> listarPorUnidade(@RequestParam Long unidadeId) {
        return ResponseEntity.ok(leituraService.listarPorUnidade(unidadeId));
    }
    
    @GetMapping("/periodo")
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "Listar leituras por período")
    public ResponseEntity<List<LeituraResponse>> listarPorPeriodo(
            @RequestParam Long unidadeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(leituraService.listarPorPeriodo(unidadeId, inicio, fim));
    }
    
    @PostMapping
    @Operation(summary = "Registrar nova leitura", description = "Endpoint público para sensores IoT")
    public ResponseEntity<LeituraResponse> registrar(@Valid @RequestBody LeituraRequest request) {
        LeituraResponse response = leituraService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

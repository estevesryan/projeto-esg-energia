package com.esg.energia.controller;

import com.esg.energia.dto.IndicadoresResponse;
import com.esg.energia.service.IndicadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/indicadores")
@SecurityRequirement(name = "bearer-jwt")
@Tag(name = "Indicadores", description = "Indicadores de consumo e eficiência energética")
public class IndicadorController {
    
    @Autowired
    private IndicadorService indicadorService;
    
    @GetMapping
    @Operation(summary = "Obter indicadores de consumo", 
               description = "Retorna indicadores de consumo diário, mensal e alertas de uma unidade")
    public ResponseEntity<IndicadoresResponse> obterIndicadores(@RequestParam Long unidadeId) {
        return ResponseEntity.ok(indicadorService.obterIndicadores(unidadeId));
    }
}

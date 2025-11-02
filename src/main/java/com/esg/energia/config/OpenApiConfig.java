package com.esg.energia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API ESG - Eficiência Energética",
        version = "1.0.0",
        description = "Sistema de monitoramento e gestão de eficiência energética com foco em sustentabilidade ESG",
        contact = @Contact(
            name = "Ryan Esteves",
            email = "ryan@esg.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Servidor Local"),
        @Server(url = "https://api-esg.exemplo.com", description = "Servidor de Produção")
    }
)
@SecurityScheme(
    name = "bearer-jwt",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Autenticação via JWT Token. Obtenha o token através do endpoint /api/auth/login"
)
public class OpenApiConfig {
}

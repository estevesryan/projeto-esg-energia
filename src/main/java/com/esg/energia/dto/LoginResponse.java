package com.esg.energia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String nome;
    private String role;
    
    public LoginResponse(String token, String username, String nome, String role) {
        this.token = token;
        this.username = username;
        this.nome = nome;
        this.role = role;
    }
}

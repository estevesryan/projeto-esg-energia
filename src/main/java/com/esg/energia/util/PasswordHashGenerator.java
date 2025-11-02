package com.esg.energia.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilitário para gerar hashes BCrypt de senhas.
 * Execute este arquivo para gerar novos hashes.
 */
public class PasswordHashGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Senhas padrão do sistema
        String adminPassword = "admin123";
        String userPassword = "user123";
        
        String adminHash = encoder.encode(adminPassword);
        String userHash = encoder.encode(userPassword);
        
        System.out.println("=".repeat(80));
        System.out.println("GERADOR DE HASHES BCRYPT - Projeto ESG");
        System.out.println("=".repeat(80));
        System.out.println();
        
        System.out.println("Senha Admin: " + adminPassword);
        System.out.println("Hash BCrypt: " + adminHash);
        System.out.println();
        
        System.out.println("Senha User: " + userPassword);
        System.out.println("Hash BCrypt: " + userHash);
        System.out.println();
        
        System.out.println("=".repeat(80));
        System.out.println("Use estes hashes no arquivo V2__seed_users.sql");
        System.out.println("=".repeat(80));
        
        // Teste de validação
        System.out.println();
        System.out.println("Validação:");
        System.out.println("Admin password matches: " + encoder.matches(adminPassword, adminHash));
        System.out.println("User password matches: " + encoder.matches(userPassword, userHash));
    }
}

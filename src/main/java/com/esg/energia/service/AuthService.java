package com.esg.energia.service;

import com.esg.energia.config.JwtTokenProvider;
import com.esg.energia.dto.LoginRequest;
import com.esg.energia.dto.LoginResponse;
import com.esg.energia.entity.Usuario;
import com.esg.energia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            
            String token = jwtTokenProvider.generateToken(request.getUsername());
            
            Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            return new LoginResponse(token, usuario.getUsername(), usuario.getNome(), usuario.getRole());
            
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}

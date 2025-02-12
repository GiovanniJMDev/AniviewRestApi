package com.aniview.aniview.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Método para obtener el nombre de usuario del token JWT
    public String getCurrentUsernameFromToken() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return token; // Implementa la decodificación JWT aquí.
    }

    // Método para obtener el correo electrónico del token JWT
    public String getCurrentEmailFromToken() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return token; // Implementa la decodificación JWT aquí.
    }

    // Método para obtener el ID del usuario desde el token JWT
    public String getCurrentUserIdFromToken() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return token; // Implementa la decodificación JWT aquí para extraer el ID del usuario.
    }
}
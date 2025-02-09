package com.aniview.aniview.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Método para obtener el nombre de usuario del token JWT
    public String getCurrentUsernameFromToken() {
        // Suponemos que el token está almacenado en el contexto de seguridad.
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Este es un ejemplo genérico.

        // Aquí puedes usar una librería para decodificar el token y obtener el "sub" (nombre de usuario).
        // Este es solo un ejemplo genérico, necesitarás usar una librería para JWT (por ejemplo jjwt) para extraer los datos del token.

        return token; // Suponemos que el token contiene el nombre de usuario. Implementa la decodificación JWT aquí.
    }

        // Método para obtener el correo electrónico del token JWT
        public String getCurrentEmailFromToken() {
            // Suponemos que el token está almacenado en el contexto de seguridad.
            String token = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
            // Aquí deberías usar una librería JWT para decodificar el token y obtener el "email"
            // Este es solo un ejemplo genérico, necesitarías implementarlo adecuadamente según cómo estés usando JWT.
    
            return token; // Devuelve el correo. Debes implementar la decodificación del token.
        }
}

package com.aniview.aniview.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniview.aniview.dto.UserDTO;
import com.aniview.aniview.entity.User;
import com.aniview.aniview.service.TokenService;
import com.aniview.aniview.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService; // Inyectamos el servicio TokenService

    // Inyección por Constructor
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService; // Inyectamos el TokenService
    }

    @PostMapping
    // public ResponseEntity<?> createUser(@RequestBody User user) {
    // try {
    // UserDTO createdUser = userService.createUser(user);
    // return ResponseEntity.ok()
    // .body(Map.of(
    // "message", "Usuario creado exitosamente",
    // "user", createdUser));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body("Error al crear usuario: " + e.getMessage());
    // }
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al obtener usuario: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al obtener lista de usuarios: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        try {
            // Ensure we don't update id, email and password
            updatedUser.setId(id);
            updatedUser.setEmail(null);
            UserDTO updated = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok()
                    .body(Map.of(
                            "message", "Usuario actualizado exitosamente",
                            "user", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // @PutMapping("/{id}/password")
    // public ResponseEntity<?> updatePassword(@PathVariable UUID id, @RequestBody
    // PasswordChangeDTO passwordDTO) {
    // try {
    // if (!passwordDTO.isValid()) {
    // return ResponseEntity.badRequest()
    // .body("Error: Solicitud de cambio de contraseña inválida. Por favor revise
    // los datos ingresados.");
    // }
    // UserDTO updated = userService.updatePassword(id, passwordDTO);
    // return ResponseEntity.ok()
    // .body(Map.of(
    // "message", "Contraseña actualizada exitosamente",
    // "user", updated));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body("Error al actualizar contraseña: " + e.getMessage());
    // }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok()
                    .body("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserInfo() {
        try {
            // Obtener el correo electrónico del token JWT
            String email = tokenService.getCurrentEmailFromToken();

            // Obtener el usuario por su correo electrónico
            UserDTO userDTO = userService.getUserByEmail(email);

            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al obtener la información del usuario: " + e.getMessage());
        }
    }

}

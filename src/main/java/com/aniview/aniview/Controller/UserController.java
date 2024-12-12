package com.aniview.aniview.Controller;

import com.aniview.aniview.Entity.User;
import com.aniview.aniview.Service.UserService;
import com.aniview.aniview.DTO.UserDTO;
import com.aniview.aniview.DTO.PasswordChangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            UserDTO createdUser = userService.createUser(user);
            return ResponseEntity.ok()
                .body(Map.of(
                    "message", "Usuario creado exitosamente",
                    "user", createdUser
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error al crear usuario: " + e.getMessage());
        }
    }

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
            updatedUser.setPassword(null);
            UserDTO updated = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok()
                .body(Map.of(
                    "message", "Usuario actualizado exitosamente",
                    "user", updated
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable UUID id, @RequestBody PasswordChangeDTO passwordDTO) {
        try {
            if (!passwordDTO.isValid()) {
                return ResponseEntity.badRequest()
                    .body("Error: Solicitud de cambio de contraseña inválida. Por favor revise los datos ingresados.");
            }
            UserDTO updated = userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok()
                .body(Map.of(
                    "message", "Contraseña actualizada exitosamente",
                    "user", updated
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error al actualizar contraseña: " + e.getMessage());
        }
    }

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
}

package com.aniview.aniview.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniview.aniview.DTO.PasswordChangeDTO;
import com.aniview.aniview.DTO.UserDTO;
import com.aniview.aniview.Entity.User;
import com.aniview.aniview.Exception.ResourceNotFoundException;
import com.aniview.aniview.Exception.UserException;
import com.aniview.aniview.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(User user) {
        // Validar campos requeridos
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new UserException("El nombre de usuario es requerido");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new UserException("El email es requerido");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new UserException("La contraseña es requerida");
        }

        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserException("El nombre de usuario ya está en uso");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserException("El email ya está registrado");
        }

        try {
            return convertToDTO(userRepository.save(user));
        } catch (Exception e) {
            throw new UserException("Error al crear el usuario: " + e.getMessage());
        }
    }

    public UserDTO getUserById(UUID id) {
        try {
            return userRepository.findById(id)
                    .map(this::convertToDTO)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        } catch (Exception e) {
            throw new UserException("Error al obtener el usuario: " + e.getMessage());
        }
    }

    public UserDTO getUserByUsername(String username) {
        try {
            UserDTO user = userRepository.findByUsername(username);
            if (user == null) {
                throw new ResourceNotFoundException("Usuario no encontrado con username: " + username);
            }
            return user;
        } catch (Exception e) {
            throw new UserException("Error al obtener el usuario: " + e.getMessage());
        }
    }

    public UserDTO getUserByEmail(String email) {
        try {
            UserDTO user = userRepository.findByEmail(email);
            if (user == null) {
                throw new ResourceNotFoundException("Usuario no encontrado con email: " + email);
            }
            return user;
        } catch (Exception e) {
            throw new UserException("Error al obtener el usuario: " + e.getMessage());
        }
    }

    public List<UserDTO> getAllUsers() {
        try {
            return userRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserException("Error al obtener la lista de usuarios: " + e.getMessage());
        }
    }

    public UserDTO updateUser(UUID id, User partialUser) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

            // Update all fields except id, email and password
            if (partialUser.getUsername() != null) {
                existingUser.setUsername(partialUser.getUsername());
            }
            if (partialUser.getName() != null) {
                existingUser.setName(partialUser.getName());
            }
            if (partialUser.getLastname() != null) {
                existingUser.setLastname(partialUser.getLastname());
            }
            // Preserve existing id, email and password
            existingUser.setId(id);

            return convertToDTO(userRepository.save(existingUser));
        } catch (Exception e) {
            throw new UserException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public UserDTO updatePassword(UUID id, PasswordChangeDTO passwordDTO) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

            existingUser.setPassword(passwordDTO.getNewPassword());

            return convertToDTO(userRepository.save(existingUser));
        } catch (Exception e) {
            throw new UserException("Error al actualizar la contraseña: " + e.getMessage());
        }
    }

    public void deleteUser(UUID id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
            }
            userRepository.deleteById(id);
            System.out.println("Usuario eliminado exitosamente: " + id);
        } catch (Exception e) {
            throw new UserException("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    private UserDTO convertToDTO(User user) {
        try {
            return new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    user.getLastname(),
                    user.getEmail());
        } catch (Exception e) {
            throw new UserException("Error al convertir usuario a DTO: " + e.getMessage());
        }
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));
    }
}
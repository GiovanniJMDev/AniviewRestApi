package com.aniview.aniview.dto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    @Test
    void testUserDTOGettersAndSetters() {
        UUID id = UUID.randomUUID();
        String username = "testUser";
        String name = "Test";
        String lastname = "User";
        String email = "test@example.com";
        String image = "testImage.jpg";

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setName(name);
        userDTO.setLastname(lastname);
        userDTO.setEmail(email);
        userDTO.setImage(image);

        assertEquals(id, userDTO.getId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(name, userDTO.getName());
        assertEquals(lastname, userDTO.getLastname());
        assertEquals(email, userDTO.getEmail());
        assertEquals(image, userDTO.getImage());
    }

    @Test
    void testUserDTOConstructor() {
        UUID id = UUID.randomUUID();
        String username = "testUser";
        String name = "Test";
        String lastname = "User";
        String email = "test@example.com";
        String image = "testImage.jpg";

        UserDTO userDTO = new UserDTO(id, username, name, lastname, email, image);

        assertEquals(id, userDTO.getId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(name, userDTO.getName());
        assertEquals(lastname, userDTO.getLastname());
        assertEquals(email, userDTO.getEmail());
        assertEquals(image, userDTO.getImage());
    }
}

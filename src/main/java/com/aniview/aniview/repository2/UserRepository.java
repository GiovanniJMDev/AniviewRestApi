package com.aniview.aniview.repository2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aniview.aniview.dto.UserDTO;
import com.aniview.aniview.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT new com.aniview.aniview.dto.UserDTO(u.id, u.username, u.name, u.lastname, u.email, u.image) FROM User u WHERE u.username = :username")
    UserDTO findByUsername(@Param("username") String username);

    @Query("SELECT new com.aniview.aniview.dto.UserDTO(u.id, u.username, u.name, u.lastname, u.email, u.image) FROM User u WHERE u.email = :email")
    UserDTO findByEmail(String email);
}

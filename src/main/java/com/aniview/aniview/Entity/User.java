package com.aniview.aniview.Entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "username", nullable = false, length = Integer.MAX_VALUE)
    private String username;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "lastname", nullable = false, length = Integer.MAX_VALUE)
    private String lastname;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "image", nullable = false, length = Integer.MAX_VALUE)
    private String image;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<AnimeList> animeLists;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<AnimeList> getAnimeLists() {
        return animeLists;
    }

    public void setAnimeLists(List<AnimeList> animeLists) {
        this.animeLists = animeLists;
    }

}
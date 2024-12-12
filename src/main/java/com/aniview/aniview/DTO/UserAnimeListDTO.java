package com.aniview.aniview.DTO;

import com.aniview.aniview.Entity.ListType;
import com.aniview.aniview.Entity.UserAnimeList;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAnimeListDTO {
    private UUID id;
    private ListType listType;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID userId;
    private String username;
    private AnimeBasicDTO anime;

    public UserAnimeListDTO(UserAnimeList entity) {
        this.id = entity.getId();
        this.listType = entity.getListType();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.anime = new AnimeBasicDTO(
            entity.getAnime().getId(),
            entity.getAnime().getTitle(),
            entity.getAnime().getImage(),
            entity.getAnime().getGenres(),
            entity.getAnime().getRating()
        );
    }

    // Getters y setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AnimeBasicDTO getAnime() {
        return anime;
    }

    public void setAnime(AnimeBasicDTO anime) {
        this.anime = anime;
    }

    // Add default constructor
    public UserAnimeListDTO() {
    }
}
package com.aniview.aniview.dto;

import java.time.Instant;
import java.util.UUID;

public class AnimeListItemDTO {
    private UUID id;
    private UUID animeListId;
    private UUID animeId;
    private Instant createdAt;
    private Instant updatedAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAnimeListId() {
        return animeListId;
    }

    public void setAnimeListId(UUID animeListId) {
        this.animeListId = animeListId;
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public void setAnimeId(UUID animeId) {
        this.animeId = animeId;
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
} 
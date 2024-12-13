// src/main/java/com/aniview/aniview/dto/AnimeListItemDTO.java
package com.aniview.aniview.DTO;

import java.time.Instant;
import java.util.UUID;

import com.aniview.aniview.Entity.ListType;

public class AnimeListItemDTO {
    private UUID userId;
    private UUID animeId;
    private ListType listType;
    private Instant addedAt;

    // Constructor
    public AnimeListItemDTO() {}

    // Add getters and setters for userId
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // Add getters and setters for listType
    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    // Getters y Setters
    public UUID getAnimeId() {
        return animeId;
    }

    public void setAnimeId(UUID animeId) {
        this.animeId = animeId;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }
}
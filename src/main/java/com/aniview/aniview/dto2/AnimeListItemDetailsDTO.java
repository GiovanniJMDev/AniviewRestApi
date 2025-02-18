package com.aniview.aniview.dto2;

import java.util.UUID;

public class AnimeListItemDetailsDTO {
    private UUID id;
    private UUID animeId;
    private String animeTitle;
    private String animeImageUrl;

    // Constructor
    public AnimeListItemDetailsDTO(UUID id, UUID animeId, String animeTitle, String animeImageUrl) {
        this.id = id;
        this.animeId = animeId;
        this.animeTitle = animeTitle;
        this.animeImageUrl = animeImageUrl;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public void setAnimeId(UUID animeId) {
        this.animeId = animeId;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public void setAnimeTitle(String animeTitle) {
        this.animeTitle = animeTitle;
    }

    public String getAnimeImageUrl() {
        return animeImageUrl;
    }

    public void setAnimeImageUrl(String animeImageUrl) {
        this.animeImageUrl = animeImageUrl;
    }
}

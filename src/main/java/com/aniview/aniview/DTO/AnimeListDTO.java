package com.aniview.aniview.DTO;

import java.util.UUID;

public class AnimeListDTO {
    private String listType;
    private UUID userId;

    // Getters y Setters
    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
} 
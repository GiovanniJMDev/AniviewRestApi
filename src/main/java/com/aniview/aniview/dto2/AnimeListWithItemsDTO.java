package com.aniview.aniview.dto2;

import java.util.List;
import java.util.UUID;

public class AnimeListWithItemsDTO {
    private UUID id;
    private String listType;
    private List<AnimeListItemDetailsDTO> items; // Change this type to match the method

    // Constructor
    public AnimeListWithItemsDTO(UUID id, String listType, List<AnimeListItemDetailsDTO> items) {
        this.id = id;
        this.listType = listType;
        this.items = items;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public List<AnimeListItemDetailsDTO> getItems() {
        return items;
    }

    public void setItems(List<AnimeListItemDetailsDTO> items) {
        this.items = items;
    }
}

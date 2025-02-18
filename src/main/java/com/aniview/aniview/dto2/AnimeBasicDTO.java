package com.aniview.aniview.dto2;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeBasicDTO {
    private UUID id;
    private String title;
    private String image;
    private List<String> genres;
    private double rating;

    public AnimeBasicDTO() {
    }

    public AnimeBasicDTO(UUID id, String title, String image, List<String> genres, double rating) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.genres = genres;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
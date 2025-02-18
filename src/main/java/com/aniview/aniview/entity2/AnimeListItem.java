package com.aniview.aniview.entity2;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anime_list_items")
public class AnimeListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private AnimeList animeList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AnimeList getAnimeList() {
        return animeList;
    }

    public void setAnimeList(AnimeList animeList) {
        this.animeList = animeList;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
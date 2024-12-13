package com.aniview.aniview.Entity;

import jakarta.persistence.*;
import java.util.UUID;

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
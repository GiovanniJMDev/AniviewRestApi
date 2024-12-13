package com.aniview.aniview.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "anime_list_items")
public class AnimeListItem {
    @EmbeddedId
    private AnimeListItemId id;

    @MapsId("listId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    private UserAnimeList list;

    @MapsId("animeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "added_at")
    private Instant addedAt;

    public AnimeListItemId getId() {
        return id;
    }

    public void setId(AnimeListItemId id) {
        this.id = id;
    }

    public UserAnimeList getList() {
        return list;
    }

    public void setList(UserAnimeList list) {
        this.list = list;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

}
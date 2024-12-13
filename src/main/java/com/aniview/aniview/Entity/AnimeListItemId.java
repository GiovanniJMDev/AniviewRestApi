package com.aniview.aniview.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AnimeListItemId implements java.io.Serializable {
    private static final long serialVersionUID = -1093224493685655391L;
    @Column(name = "list_id", nullable = false)
    private UUID listId;

    @Column(name = "anime_id", nullable = false)
    private UUID animeId;

    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public void setAnimeId(UUID animeId) {
        this.animeId = animeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimeListItemId entity = (AnimeListItemId) o;
        return Objects.equals(this.listId, entity.listId) &&
                Objects.equals(this.animeId, entity.animeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, animeId);
    }

}
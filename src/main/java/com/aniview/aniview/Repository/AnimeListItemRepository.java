package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.AnimeListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimeListItemRepository extends JpaRepository<AnimeListItem, UUID> {
    List<AnimeListItem> findByAnimeListId(UUID animeListId);
} 
package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.AnimeListItem;
import com.aniview.aniview.Entity.AnimeListItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeListItemRepository extends JpaRepository<AnimeListItem, AnimeListItemId> {
    List<AnimeListItem> findByListId(UUID listId);
}

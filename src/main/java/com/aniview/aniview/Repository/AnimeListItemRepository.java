package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.AnimeListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimeListItemRepository extends JpaRepository<AnimeListItem, UUID> {

    List<AnimeListItem> findByAnimeListId(UUID animeListId);

    @Query("SELECT ali FROM AnimeListItem ali JOIN FETCH ali.anime WHERE ali.animeList.id = :animeListId")
    List<AnimeListItem> findByAnimeListIdWithAnime(@Param("animeListId") UUID animeListId);

}
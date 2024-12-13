package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.AnimeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimeListRepository extends JpaRepository<AnimeList, UUID> {
    List<AnimeList> findByUserId(UUID userId);
} 
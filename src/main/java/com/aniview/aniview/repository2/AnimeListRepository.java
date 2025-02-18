package com.aniview.aniview.repository2;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aniview.aniview.entity.AnimeList;

@Repository
public interface AnimeListRepository extends JpaRepository<AnimeList, UUID> {
    List<AnimeList> findByUserId(UUID userId);
} 
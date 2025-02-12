package com.aniview.aniview.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aniview.aniview.entity.Anime;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, UUID> {
}

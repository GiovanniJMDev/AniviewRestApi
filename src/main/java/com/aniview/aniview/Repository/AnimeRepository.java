package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.Anime;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, UUID> {
}

package com.aniview.aniview.Repository;

import com.aniview.aniview.Entity.UserAnimeList;
import com.aniview.aniview.Entity.User;
import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Entity.ListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAnimeListRepository extends JpaRepository<UserAnimeList, UUID> {
    Optional<UserAnimeList> findByUserAndAnimeAndListType(User user, Anime anime, ListType listType);
    List<UserAnimeList> findByUserId(UUID userId);
    List<UserAnimeList> findByAnimeId(UUID animeId);
    List<UserAnimeList> findByUserIdAndListType(UUID userId, ListType listType);
} 
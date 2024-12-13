package com.aniview.aniview.Controller;

import com.aniview.aniview.Entity.UserAnimeList;
import com.aniview.aniview.DTO.AnimeListItemDTO;
import com.aniview.aniview.Service.UserAnimeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/anime-lists")
public class AnimeListItemController {

    @Autowired
    private UserAnimeListService userAnimeListService;

    @PostMapping
    public ResponseEntity<UserAnimeListDTO> addAnimeToList(@RequestBody AnimeListItemDTO dto) {
        return ResponseEntity.ok(userAnimeListService.addAnimeToList(
            dto.getUserId(), 
            dto.getAnimeId(), 
            dto.getListType()
        ));
    }

    @DeleteMapping("/{userId}/{animeId}/{listType}")
    public ResponseEntity<Void> removeAnimeFromList(
            @PathVariable UUID userId, 
            @PathVariable UUID animeId,
            @PathVariable ListType listType) {
        userAnimeListService.removeAnimeFromList(userId, animeId, listType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/{listType}")
    public ResponseEntity<List<UserAnimeList>> getAnimesByUserAndListType(
            @PathVariable UUID userId,
            @PathVariable ListType listType) {
        return ResponseEntity.ok(userAnimeListService.getUserAnimeListsByUserIdAndListType(userId, listType));
    }
}

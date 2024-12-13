package com.aniview.aniview.Controller;

import com.aniview.aniview.DTO.AnimeListDTO;
import com.aniview.aniview.Entity.AnimeList;
import com.aniview.aniview.Exception.ResourceNotFoundException;
import com.aniview.aniview.Service.AnimeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/anime-lists")
public class AnimeListController {

    @Autowired
    private AnimeListService animeListService;

    // Create
    @PostMapping
    public ResponseEntity<?> createAnimeList(@RequestBody AnimeListDTO animeListDTO) {
        try {
            AnimeList createdList = animeListService.createAnimeList(animeListDTO);
            return ResponseEntity.ok(createdList);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Error creating anime list: " + e.getMessage()));
        }
    }

    // Read
    @GetMapping
    public ResponseEntity<List<AnimeList>> getAllAnimeLists() {
        return ResponseEntity.ok(animeListService.getAllAnimeLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeList> getAnimeListById(@PathVariable UUID id) {
        return animeListService.getAnimeListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnimeList>> getAnimeListsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(animeListService.getAnimeListsByUserId(userId));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<AnimeList> updateAnimeList(
            @PathVariable UUID id,
            @RequestBody AnimeList animeListDetails) {
        return ResponseEntity.ok(animeListService.updateAnimeList(id, animeListDetails));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimeList(@PathVariable UUID id) {
        animeListService.deleteAnimeList(id);
        return ResponseEntity.ok().build();
    }
} 
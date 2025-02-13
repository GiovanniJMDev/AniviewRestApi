package com.aniview.aniview.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniview.aniview.dto.AnimeListDTO;
import com.aniview.aniview.dto.AnimeListWithItemsDTO;
import com.aniview.aniview.exception.ResourceNotFoundException;
import com.aniview.aniview.service.AnimeListService;

@RestController
@RequestMapping("/api/anime-lists")
public class AnimeListController {

    @Autowired
    private AnimeListService animeListService;

    // Create
    @PostMapping
    public ResponseEntity<Map<String, Object>> createAnimeList(@RequestBody AnimeListDTO animeListDTO) {
        try {
            AnimeListDTO createdList = animeListService.createAnimeList(animeListDTO);
            return ResponseEntity.ok(Map.of("message", "Anime list created successfully", "data", createdList));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not Found", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal Server Error", "message",
                            "Error creating anime list: " + e.getMessage()));
        }
    }

    // Read
    @GetMapping
    public ResponseEntity<List<AnimeListDTO>> getAllAnimeLists() {
        return ResponseEntity.ok(animeListService.getAllAnimeLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeListDTO> getAnimeListById(@PathVariable UUID id) {
        return animeListService.getAnimeListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnimeListWithItemsDTO>> getAnimeListsWithItemsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(animeListService.getAnimeListsWithItemsByUserId(userId));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAnimeList(
            @PathVariable UUID id,
            @RequestBody AnimeListDTO animeListDTO) {
        try {
            AnimeListDTO updated = animeListService.updateAnimeList(id, animeListDTO);
            return ResponseEntity.ok(Map.of("message", "Anime list updated successfully", "data", updated));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Not Found", "message", e.getMessage()));
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimeList(@PathVariable UUID id) {
        animeListService.deleteAnimeList(id);
        return ResponseEntity.ok().build();
    }
}
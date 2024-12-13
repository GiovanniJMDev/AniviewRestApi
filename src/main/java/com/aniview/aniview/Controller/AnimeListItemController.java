package com.aniview.aniview.Controller;

import com.aniview.aniview.DTO.AnimeListItemDTO;
import com.aniview.aniview.Service.AnimeListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/anime-list-items")
public class AnimeListItemController {

    @Autowired
    private AnimeListItemService animeListItemService;

    @GetMapping("/{id}")
    public ResponseEntity<AnimeListItemDTO> getAnimeListItem(@PathVariable UUID id) {
        return animeListItemService.getAnimeListItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<AnimeListItemDTO>> getAnimeListItemsByListId(@PathVariable UUID listId) {
        List<AnimeListItemDTO> items = animeListItemService.getAnimeListItemsByListId(listId);
        return ResponseEntity.ok(items);
    }

    @GetMapping
    public ResponseEntity<List<AnimeListItemDTO>> getAllAnimeListItems() {
        List<AnimeListItemDTO> items = animeListItemService.getAllAnimeListItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<AnimeListItemDTO> createAnimeListItem(@RequestBody AnimeListItemDTO dto) {
        AnimeListItemDTO created = animeListItemService.createAnimeListItem(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeListItemDTO> updateAnimeListItem(
            @PathVariable UUID id,
            @RequestBody AnimeListItemDTO dto) {
        AnimeListItemDTO updated = animeListItemService.updateAnimeListItem(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimeListItem(@PathVariable UUID id) {
        animeListItemService.deleteAnimeListItem(id);
        return ResponseEntity.noContent().build();
    }
} 
package com.aniview.aniview.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Service.AnimeService;
import com.aniview.aniview.DTO.AnimeDTO;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {
    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<AnimeDTO>> getAllAnimes() {
        List<AnimeDTO> animes = animeService.getAllAnimes();
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTO> getAnimeById(@PathVariable UUID id) {
        return animeService.getAnimeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genre")
    public ResponseEntity<?> handleMissingGenre() {
        return ResponseEntity.badRequest().body("Debe seleccionar un género.");
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> getAnimesByGenre(@PathVariable String genre) {
        try {
            List<AnimeDTO> animes = animeService.findByGenre(genre);
            return ResponseEntity.ok(animes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }    

    @GetMapping("/random")
    public ResponseEntity<AnimeDTO> getRandomAnimeByGenres(@RequestParam List<String> genres) {
        return animeService.findRandomAnimeByGenres(genres)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnimeDTO> addAnime(@RequestBody Anime anime) {
        AnimeDTO createdAnime = animeService.addAnime(anime);
        return ResponseEntity.ok(createdAnime);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeDTO> updateAnime(@PathVariable UUID id, @RequestBody Anime anime) {
        return animeService.updateAnime(id, anime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable UUID id) {
        if (animeService.deleteAnime(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

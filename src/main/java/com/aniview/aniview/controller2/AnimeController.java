package com.aniview.aniview.controller2;

import java.util.Collections;
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

import com.aniview.aniview.dto.AnimeDTO;
import com.aniview.aniview.entity.Anime;
import com.aniview.aniview.service.AnimeService;

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
    public ResponseEntity<String> handleMissingGenre() {
        return ResponseEntity.badRequest().body("Debe seleccionar un género.");
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<AnimeDTO>> getAnimesByGenre(@PathVariable String genre) {
        if (genre == null || genre.isBlank()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<AnimeDTO> animes = animeService.findByGenre(genre);
        return animes.isEmpty()
                ? ResponseEntity.badRequest().body(Collections.emptyList())
                : ResponseEntity.ok(animes);
    }

    @GetMapping("/random")
    public ResponseEntity<AnimeDTO> getRandomAnimeByGenres(@RequestParam(required = false) List<String> genres) {
        // Si la lista de géneros está vacía o no se pasa, busca un anime aleatorio sin
        // filtro de género
        if (genres == null || genres.isEmpty()) {
            return animeService.findRandomAnime()
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // Si se pasan géneros, busca un anime aleatorio que coincida con los géneros
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

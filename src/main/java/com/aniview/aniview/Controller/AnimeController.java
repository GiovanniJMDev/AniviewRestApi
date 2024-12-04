package com.aniview.aniview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/anime")
public class AnimeController {
    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAllAnimes() {
        List<Anime> animes = animeService.getAllAnimes();
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
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
            List<Anime> animes = animeService.findByGenre(genre);
            return ResponseEntity.ok(animes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }    

    @GetMapping("/random")
    public ResponseEntity<Anime> getRandomAnimeByGenres(@RequestParam List<String> genres) {
        return animeService.findRandomAnimeByGenres(genres)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Anime> addAnime(@RequestBody Anime anime) {
        Anime createdAnime = animeService.addAnime(anime);
        return ResponseEntity.ok(createdAnime);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime anime) {
        return animeService.updateAnime(id, anime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        if (animeService.deleteAnime(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.aniview.aniview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}

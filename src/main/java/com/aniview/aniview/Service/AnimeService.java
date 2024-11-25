package com.aniview.aniview.Service;

import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Optional<Anime> getAnimeById(Long id) {
        return animeRepository.findById(id);
    }
}

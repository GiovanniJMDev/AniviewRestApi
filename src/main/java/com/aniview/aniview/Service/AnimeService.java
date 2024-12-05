package com.aniview.aniview.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Repository.AnimeRepository;

@Service // Esta anotación convierte esta clase en un bean gestionado por Spring.
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Optional<Anime> getAnimeById(Long id) {
        return animeRepository.findById(id);
    }

    public List<Anime> findByGenre(String genre) {
        System.out.println("genre: " + genre);
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un género.");
        }

        List<Anime> allAnimes = animeRepository.findAll();
        List<Anime> filteredAnimes = allAnimes.stream()
                .filter(anime -> anime.getGenres().contains(genre))
                .collect(Collectors.toList());

        if (filteredAnimes.isEmpty()) {
            throw new IllegalArgumentException("El género proporcionado no es válido o no tiene animes asociados.");
        }

        return filteredAnimes;
    }

    public Optional<Anime> findRandomAnimeByGenres(List<String> genres) {
        if (genres == null || genres.isEmpty()) {
            return Optional.empty();
        }

        // Paso 1: Seleccionar un género aleatorio de la lista proporcionada
        Random random = new Random();
        String randomGenre = genres.get(random.nextInt(genres.size()));

        // Paso 2: Buscar animes que contengan ese género
        List<Anime> matchingAnimes = findByGenre(randomGenre);

        if (matchingAnimes.isEmpty()) {
            return Optional.empty();
        }

        // Paso 3: Elegir un anime aleatorio de los encontrados
        return Optional.of(matchingAnimes.get(random.nextInt(matchingAnimes.size())));
    }

    public Anime addAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    public Optional<Anime> updateAnime(Long id, Anime anime) {
        return animeRepository.findById(id).map(existingAnime -> {
            existingAnime.setTitle(anime.getTitle());
            existingAnime.setGenres(anime.getGenres());
            existingAnime.setDescription(anime.getDescription());
            existingAnime.setImage(anime.getImage());
            existingAnime.setRating(anime.getRating());
            existingAnime.setSeasons(anime.getSeasons());
            existingAnime.setYearStarted(anime.getYearStarted());
            existingAnime.setYearEnded(anime.getYearEnded());
            return animeRepository.save(existingAnime);
        });
    }

    public boolean deleteAnime(Long id) {
        return animeRepository.findById(id).map(anime -> {
            animeRepository.delete(anime);
            return true;
        }).orElse(false);
    }
}/* End of Selection */

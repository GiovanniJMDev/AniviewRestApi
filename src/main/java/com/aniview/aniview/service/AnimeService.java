package com.aniview.aniview.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aniview.aniview.dto.AnimeDTO;
import com.aniview.aniview.entity.Anime;
import com.aniview.aniview.repository.AnimeRepository;

@Service
public class AnimeService {

    private static final Logger log = LoggerFactory.getLogger(AnimeService.class);
    private static final SecureRandom secureRandom = new SecureRandom(); // Reemplaza Random por SecureRandom

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<AnimeDTO> getAllAnimes() {
        return animeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AnimeDTO> getAnimeById(UUID id) {
        return animeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<AnimeDTO> findByGenre(String genre) {
        log.info("genre: " + genre);
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un género.");
        }

        List<Anime> allAnimes = animeRepository.findAll();
        List<AnimeDTO> filteredAnimes = allAnimes.stream()
                .filter(anime -> anime.getGenres().contains(genre))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (filteredAnimes.isEmpty()) {
            throw new IllegalArgumentException("El género proporcionado no es válido o no tiene animes asociados.");
        }

        return filteredAnimes;
    }

    public Optional<AnimeDTO> findRandomAnimeByGenres(List<String> genres) {
        if (genres == null || genres.isEmpty()) {
            List<AnimeDTO> allAnimes = getAllAnimes();
            if (allAnimes.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(allAnimes.get(secureRandom.nextInt(allAnimes.size())));
        }

        String randomGenre = genres.get(secureRandom.nextInt(genres.size()));
        List<AnimeDTO> matchingAnimes = findByGenre(randomGenre);

        if (matchingAnimes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(matchingAnimes.get(secureRandom.nextInt(matchingAnimes.size())));
    }

    public AnimeDTO addAnime(Anime anime) {
        Anime savedAnime = animeRepository.save(anime);
        return convertToDTO(savedAnime);
    }

    public Optional<AnimeDTO> updateAnime(UUID id, Anime anime) {
        return animeRepository.findById(id).map(existingAnime -> {
            existingAnime.setTitle(anime.getTitle());
            existingAnime.setGenres(anime.getGenres());
            existingAnime.setDescription(anime.getDescription());
            existingAnime.setImage(anime.getImage());
            existingAnime.setRating(anime.getRating());
            existingAnime.setSeasons(anime.getSeasons());
            existingAnime.setYearStarted(anime.getYearStarted());
            existingAnime.setYearEnded(anime.getYearEnded());
            existingAnime.setPlatforms(anime.getPlatforms());
            existingAnime.setEpisodesPerSeason(anime.getEpisodesPerSeason());
            existingAnime.setTotalViews(anime.getTotalViews());
            existingAnime.setWeeklyViews(anime.getWeeklyViews());
            return convertToDTO(animeRepository.save(existingAnime));
        });
    }

    public Optional<AnimeDTO> findRandomAnime() {
        List<AnimeDTO> allAnimes = getAllAnimes();
        if (allAnimes.isEmpty()) {
            return Optional.empty();
        }

        // Selecciona un anime aleatorio con SecureRandom
        AnimeDTO randomAnime = allAnimes.get(secureRandom.nextInt(allAnimes.size()));
        return Optional.of(randomAnime);
    }

    public boolean deleteAnime(UUID id) {
        return animeRepository.findById(id).map(anime -> {
            animeRepository.delete(anime);
            return true;
        }).orElse(false);
    }

    private AnimeDTO convertToDTO(Anime anime) {
        AnimeDTO dto = new AnimeDTO(
                anime.getId(),
                anime.getTitle(),
                anime.getImage(),
                anime.getGenres(),
                anime.getRating());
        dto.setDescription(anime.getDescription());
        dto.setPlatforms(anime.getPlatforms());
        dto.setYearStarted(anime.getYearStarted());
        dto.setYearEnded(anime.getYearEnded());
        dto.setSeasons(anime.getSeasons());
        dto.setEpisodesPerSeason(anime.getEpisodesPerSeason().stream()
                .map(obj -> (Integer) obj)
                .collect(Collectors.toList()));
        dto.setTotalViews(anime.getTotalViews());
        dto.setWeeklyViews(anime.getWeeklyViews());
        return dto;
    }

    public Optional<Anime> findById(UUID id) {
        return animeRepository.findById(id);
    }
}

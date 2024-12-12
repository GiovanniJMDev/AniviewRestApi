package com.aniview.aniview.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aniview.aniview.DTO.AnimeDTO;
import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Repository.AnimeRepository;

@Service
public class AnimeService {

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
        System.out.println("genre: " + genre);
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
        Random random = new Random();

        if (genres == null || genres.isEmpty()) {
            List<AnimeDTO> allAnimes = getAllAnimes();
            if (allAnimes.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(allAnimes.get(random.nextInt(allAnimes.size())));
        }

        String randomGenre = genres.get(random.nextInt(genres.size()));
        List<AnimeDTO> matchingAnimes = findByGenre(randomGenre);

        if (matchingAnimes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(matchingAnimes.get(random.nextInt(matchingAnimes.size())));
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
            anime.getRating()
        );
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
}

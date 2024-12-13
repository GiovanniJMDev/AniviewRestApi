package com.aniview.aniview.Service;

import com.aniview.aniview.DTO.AnimeListItemDTO;
import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Entity.AnimeList;
import com.aniview.aniview.Entity.AnimeListItem;
import com.aniview.aniview.Exception.ResourceNotFoundException;
import com.aniview.aniview.Repository.AnimeListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimeListItemService {

    @Autowired
    private AnimeListItemRepository animeListItemRepository;

    @Autowired
    private AnimeListService animeListService;

    @Autowired
    private AnimeService animeService;

    @Transactional
    public AnimeListItemDTO createAnimeListItem(AnimeListItemDTO dto) {
        AnimeList animeList = animeListService.findAnimeListById(dto.getAnimeListId())
                .orElseThrow(() -> new ResourceNotFoundException("AnimeList not found"));
        Anime anime = animeService.findById(dto.getAnimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Anime not found"));

        // Check if the anime is already in the list
        boolean exists = animeListItemRepository.findByAnimeListId(animeList.getId()).stream()
                .anyMatch(item -> item.getAnime().getId().equals(anime.getId()));
        if (exists) {
            throw new IllegalArgumentException("El anime ya está en la lista.");
        }

        AnimeListItem item = new AnimeListItem();
        item.setAnimeList(animeList);
        item.setAnime(anime);

        AnimeListItem saved = animeListItemRepository.save(item);
        return convertToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<AnimeListItemDTO> getAnimeListItemsByListId(UUID listId) {
        return animeListItemRepository.findByAnimeListId(listId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<AnimeListItemDTO> getAnimeListItemById(UUID id) {
        return animeListItemRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional
    public AnimeListItemDTO updateAnimeListItem(UUID id, AnimeListItemDTO dto) {
        AnimeListItem item = animeListItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnimeListItem not found"));

        // item.setUpdatedAt(Instant.now());

        AnimeListItem saved = animeListItemRepository.save(item);
        return convertToDTO(saved);
    }

    @Transactional
    public void deleteAnimeListItem(UUID id) {
        animeListItemRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimeListItemDTO> getAllAnimeListItems() {
        return animeListItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private AnimeListItemDTO convertToDTO(AnimeListItem item) {
        AnimeListItemDTO dto = new AnimeListItemDTO();
        dto.setId(item.getId());
        dto.setAnimeListId(item.getAnimeList().getId());
        dto.setAnimeId(item.getAnime().getId());
        // dto.setCreatedAt(item.getCreatedAt());
        // dto.setUpdatedAt(item.getUpdatedAt());
        return dto;
    }
} 
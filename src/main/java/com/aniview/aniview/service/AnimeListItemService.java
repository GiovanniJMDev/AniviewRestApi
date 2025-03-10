package com.aniview.aniview.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aniview.aniview.dto.AnimeListDTO;
import com.aniview.aniview.dto.AnimeListItemDTO;
import com.aniview.aniview.entity.Anime;
import com.aniview.aniview.entity.AnimeList;
import com.aniview.aniview.entity.AnimeListItem;
import com.aniview.aniview.exception.ResourceNotFoundException;
import com.aniview.aniview.repository.AnimeListItemRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("AnimeList no encontrado"));
        Anime anime = animeService.findById(dto.getAnimeId())
                .orElseThrow(() -> new ResourceNotFoundException("Anime no encontrado"));

        // Obtener todas las listas del usuario
        List<AnimeListDTO> userAnimeLists = animeListService.getAnimeListsByUserId(animeList.getUser().getId());

        // Verificar si el anime ya está en alguna lista del usuario
        boolean existsInUserLists = userAnimeLists.stream()
                .flatMap(list -> animeListItemRepository.findByAnimeListId(list.getId()).stream())
                .anyMatch(item -> item.getAnime().getId().equals(anime.getId()));

        if (existsInUserLists) {
            throw new IllegalArgumentException("El anime ya está en una de tus listas.");
        }

        AnimeListItem item = new AnimeListItem();
        item.setAnimeList(animeList);
        item.setAnime(anime);

        AnimeListItem saved = animeListItemRepository.save(item);
        return convertToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<AnimeListItemDTO> getAnimeListItemsByListId(UUID listId) {
        List<AnimeListItemDTO> items = animeListItemRepository.findByAnimeListId(listId).stream()
                .map(this::convertToDTO)
                .toList();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No hay datos disponibles para esta lista.");
        }
        return items;
    }

    @Transactional(readOnly = true)
    public Optional<AnimeListItemDTO> getAnimeListItemById(UUID id) {
        Optional<AnimeListItemDTO> item = animeListItemRepository.findById(id)
                .map(this::convertToDTO);
        if (item.isEmpty()) {
            throw new ResourceNotFoundException("AnimeListItem no encontrado con id: " + id);
        }
        return item;
    }

    @Transactional
    public AnimeListItemDTO updateAnimeListItem(UUID id, UUID newAnimeListId) {
        // Buscar el AnimeListItem por su ID
        AnimeListItem item = animeListItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnimeListItem no encontrado"));

        // Buscar la nueva lista de anime usando el nuevo animeListId
        AnimeList newAnimeList = animeListService.findAnimeListById(newAnimeListId)
                .orElseThrow(() -> new ResourceNotFoundException("AnimeList no encontrado con id: " + newAnimeListId));

        // Actualizar la relación del AnimeList en el AnimeListItem
        item.setAnimeList(newAnimeList); // Establecemos la nueva lista de anime

        // Guardar el AnimeListItem actualizado
        AnimeListItem saved = animeListItemRepository.save(item);

        // Convertir el objeto AnimeListItem a AnimeListItemDTO para devolverlo
        return convertToDTO(saved);
    }

    @Transactional
    public void deleteAnimeListItem(UUID id) {
        if (!animeListItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("AnimeListItem no encontrado");
        }
        animeListItemRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimeListItemDTO> getAllAnimeListItems() {
        List<AnimeListItemDTO> items = animeListItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No hay datos disponibles.");
        }
        return items;
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
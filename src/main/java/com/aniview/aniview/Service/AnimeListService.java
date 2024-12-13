package com.aniview.aniview.Service;

import com.aniview.aniview.DTO.AnimeListDTO;
import com.aniview.aniview.Entity.AnimeList;
import com.aniview.aniview.Entity.User;
import com.aniview.aniview.Repository.AnimeListRepository;
import com.aniview.aniview.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimeListService {

    @Autowired
    private AnimeListRepository animeListRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public AnimeListDTO createAnimeList(AnimeListDTO dto) {
        try {
            User user = userService.findById(dto.getUserId());
            
            AnimeList animeList = new AnimeList();
            animeList.setListType(dto.getListType());
            animeList.setUser(user);
            animeList.setCreatedAt(Instant.now());
            animeList.setUpdatedAt(Instant.now());
            
            AnimeList saved = animeListRepository.save(animeList);
            return convertToDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create anime list", e);
        }
    }

    @Transactional(readOnly = true)
    public List<AnimeListDTO> getAllAnimeLists() {
        return animeListRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private AnimeListDTO convertToDTO(AnimeList animeList) {
        AnimeListDTO dto = new AnimeListDTO();
        dto.setId(animeList.getId());
        dto.setUserId(animeList.getUser().getId());
        dto.setListType(animeList.getListType());
        dto.setCreatedAt(animeList.getCreatedAt());
        dto.setUpdatedAt(animeList.getUpdatedAt());
        return dto;
    }

    @Transactional(readOnly = true)
    public Optional<AnimeListDTO> getAnimeListById(UUID id) {
        return animeListRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public List<AnimeListDTO> getAnimeListsByUserId(UUID userId) {
        return animeListRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public AnimeListDTO updateAnimeList(UUID id, AnimeListDTO animeListDTO) {
        AnimeList animeList = animeListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnimeList not found with id: " + id));
        
        animeList.setListType(animeListDTO.getListType());
        animeList.setUpdatedAt(Instant.now());
        
        AnimeList saved = animeListRepository.save(animeList);
        return convertToDTO(saved);
    }

    public void deleteAnimeList(UUID id) {
        animeListRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<AnimeList> findAnimeListById(UUID id) {
        return animeListRepository.findById(id);
    }
}
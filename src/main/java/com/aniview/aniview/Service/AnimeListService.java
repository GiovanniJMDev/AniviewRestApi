package com.aniview.aniview.Service;

import com.aniview.aniview.DTO.AnimeListDTO;
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
    public AnimeList createAnimeList(AnimeListDTO dto) {
        try {
            User user = userService.findById(dto.getUserId());
            
            AnimeList animeList = new AnimeList();
            animeList.setListType(dto.getListType());
            animeList.setUser(user);
            animeList.setCreatedAt(Instant.now());
            animeList.setUpdatedAt(Instant.now());
            
            return animeListRepository.save(animeList);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create anime list", e);
        }
    }

    public List<AnimeList> getAllAnimeLists() {
        return animeListRepository.findAll();
    }

    public Optional<AnimeList> getAnimeListById(UUID id) {
        return animeListRepository.findById(id);
    }

    public List<AnimeList> getAnimeListsByUserId(UUID userId) {
        return animeListRepository.findByUserId(userId);
    }

    public AnimeList updateAnimeList(UUID id, AnimeList animeListDetails) {
        AnimeList animeList = animeListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnimeList not found with id: " + id));
        
        animeList.setListType(animeListDetails.getListType());
        animeList.setUpdatedAt(Instant.now());
        
        return animeListRepository.save(animeList);
    }

    public void deleteAnimeList(UUID id) {
        animeListRepository.deleteById(id);
    }
}
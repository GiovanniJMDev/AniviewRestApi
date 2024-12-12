package com.aniview.aniview.Service;

import com.aniview.aniview.Entity.UserAnimeList;
import com.aniview.aniview.Entity.ListType;
import com.aniview.aniview.Repository.UserAnimeListRepository;
import com.aniview.aniview.Repository.AnimeRepository;
import com.aniview.aniview.Repository.UserRepository;
import com.aniview.aniview.Entity.User;
import com.aniview.aniview.Entity.Anime;
import com.aniview.aniview.Exception.ResourceNotFoundException;
import com.aniview.aniview.DTO.UserAnimeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.time.Instant;
import java.util.Optional;

@Service
public class UserAnimeListService {
    
    @Autowired
    private UserAnimeListRepository userAnimeListRepository;

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private UserRepository userRepository;

    public UserAnimeList createUserAnimeList(UserAnimeList userAnimeList) {
        validateListType(userAnimeList.getListType());
        return userAnimeListRepository.save(userAnimeList);
    }

    public UserAnimeList getUserAnimeListById(UUID id) {
        return userAnimeListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista no encontrada con id: " + id));
    }

    public List<UserAnimeListDTO> getAllUserAnimeLists() {
        return userAnimeListRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<UserAnimeList> getUserAnimeListsByUserId(UUID userId) {
        return userAnimeListRepository.findByUserId(userId);
    }

    public List<UserAnimeList> getUserAnimeListsByAnimeId(UUID animeId) {
        return userAnimeListRepository.findByAnimeId(animeId);
    }

    public List<UserAnimeList> getUserAnimeListsByUserIdAndListType(UUID userId, ListType listType) {
        validateListType(listType);
        return userAnimeListRepository.findByUserIdAndListType(userId, listType);
    }

    public UserAnimeList updateUserAnimeList(UserAnimeList userAnimeList) {
        if (!userAnimeListRepository.existsById(userAnimeList.getId())) {
            throw new ResourceNotFoundException("Lista no encontrada con id: " + userAnimeList.getId());
        }
        validateListType(userAnimeList.getListType());
        return userAnimeListRepository.save(userAnimeList);
    }

    public void deleteUserAnimeList(UUID id) {
        if (!userAnimeListRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lista no encontrada con id: " + id);
        }
        userAnimeListRepository.deleteById(id);
    }

    private void validateListType(ListType listType) {
        if (listType == null) {
            throw new IllegalArgumentException("El tipo de lista no puede ser nulo");
        }
    }

    public UserAnimeListDTO mapToDTO(UserAnimeList entity) {
        return new UserAnimeListDTO(entity); // Solo esto es necesario
    }

    public UserAnimeListDTO addAnimeToList(UUID userId, UUID animeId, ListType listType) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
        Anime anime = animeRepository.findById(animeId)
            .orElseThrow(() -> new ResourceNotFoundException("Anime not found"));
            
        // Check if anime is already in the list
        Optional<UserAnimeList> existingEntry = userAnimeListRepository
            .findByUserAndAnimeAndListType(user, anime, listType);
            
        if (existingEntry.isPresent()) {
            throw new IllegalStateException("Anime already exists in this list");
        }

        UserAnimeList newEntry = new UserAnimeList();
        newEntry.setUser(user);
        newEntry.setAnime(anime);
        newEntry.setListType(listType);
        newEntry.setCreatedAt(Instant.now());
        newEntry.setUpdatedAt(Instant.now());

        UserAnimeList savedEntry = userAnimeListRepository.save(newEntry);
        return new UserAnimeListDTO(savedEntry);
    }

    public void removeAnimeFromList(UUID userId, UUID animeId, ListType listType) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
        Anime anime = animeRepository.findById(animeId)
            .orElseThrow(() -> new ResourceNotFoundException("Anime not found"));
            
        UserAnimeList entry = userAnimeListRepository
            .findByUserAndAnimeAndListType(user, anime, listType)
            .orElseThrow(() -> new ResourceNotFoundException("Anime not found in this list"));

        userAnimeListRepository.delete(entry);
    }
} 
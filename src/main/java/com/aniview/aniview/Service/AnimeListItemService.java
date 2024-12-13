package com.aniview.aniview.Service;

import com.aniview.aniview.Entity.AnimeListItem;
import com.aniview.aniview.Entity.AnimeListItemId;
import com.aniview.aniview.DTO.AnimeListItemDTO;
import com.aniview.aniview.Repository.AnimeListItemRepository;
import com.aniview.aniview.Repository.AnimeRepository;
import com.aniview.aniview.Repository.UserAnimeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AnimeListItemService {

    @Autowired
    private AnimeListItemRepository animeListItemRepository;

    @Autowired
    private UserAnimeListRepository userAnimeListRepository;

    @Autowired
    private AnimeRepository animeRepository;

    public AnimeListItem addAnimeToList(AnimeListItemDTO dto) {
        AnimeListItem item = new AnimeListItem();
        AnimeListItemId id = new AnimeListItemId();
        id.setListId(dto.getListId());
        id.setAnimeId(dto.getAnimeId());
        
        item.setId(id);
        item.setList(userAnimeListRepository.findById(dto.getListId())
                .orElseThrow(() -> new RuntimeException("List not found")));
        item.setAnime(animeRepository.findById(dto.getAnimeId())
                .orElseThrow(() -> new RuntimeException("Anime not found")));
        item.setAddedAt(Instant.now());
        
        return animeListItemRepository.save(item);
    }

    public void removeAnimeFromList(UUID listId, UUID animeId) {
        AnimeListItemId id = new AnimeListItemId();
        id.setListId(listId);
        id.setAnimeId(animeId);
        animeListItemRepository.deleteById(id);
    }

    public List<AnimeListItem> getAnimesByListId(UUID listId) {
        return animeListItemRepository.findByListId(listId);
    }

    public boolean existsInList(UUID listId, UUID animeId) {
        AnimeListItemId id = new AnimeListItemId();
        id.setListId(listId);
        id.setAnimeId(animeId);
        return animeListItemRepository.existsById(id);
    }
}

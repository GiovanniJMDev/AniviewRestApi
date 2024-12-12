package com.aniview.aniview.Controller;

import com.aniview.aniview.Entity.ListType;
import com.aniview.aniview.Entity.UserAnimeList;
import com.aniview.aniview.Service.UserAnimeListService;
import com.aniview.aniview.DTO.UserAnimeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-anime-lists")
public class UserAnimeListController {

    @Autowired
    private UserAnimeListService userAnimeListService;

    @PostMapping
    public ResponseEntity<UserAnimeList> createUserAnimeList(@RequestBody UserAnimeList userAnimeList) {
        return ResponseEntity.ok(userAnimeListService.createUserAnimeList(userAnimeList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAnimeListDTO> getUserAnimeListById(@PathVariable UUID id) {
        UserAnimeList list = userAnimeListService.getUserAnimeListById(id);
        return ResponseEntity.ok(new UserAnimeListDTO(list));
    }

    @GetMapping
    public ResponseEntity<List<UserAnimeListDTO>> getAllUserAnimeLists() {
        return ResponseEntity.ok(userAnimeListService.getAllUserAnimeLists());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAnimeListDTO>> getUserAnimeListsByUserId(@PathVariable UUID userId) {
        List<UserAnimeList> lists = userAnimeListService.getUserAnimeListsByUserId(userId);
        List<UserAnimeListDTO> dtos = lists.stream()
            .map(UserAnimeListDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/anime/{animeId}")
    public ResponseEntity<List<UserAnimeListDTO>> getUserAnimeListsByAnimeId(@PathVariable UUID animeId) {
        List<UserAnimeList> lists = userAnimeListService.getUserAnimeListsByAnimeId(animeId);
        List<UserAnimeListDTO> dtos = lists.stream()
            .map(UserAnimeListDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/user/{userId}/type/{listType}")
    public ResponseEntity<List<UserAnimeList>> getUserAnimeListsByUserIdAndListType(
            @PathVariable UUID userId,
            @PathVariable ListType listType) {
        return ResponseEntity.ok(userAnimeListService.getUserAnimeListsByUserIdAndListType(userId, listType));
    }

    @PutMapping
    public ResponseEntity<UserAnimeList> updateUserAnimeList(@RequestBody UserAnimeList userAnimeList) {
        return ResponseEntity.ok(userAnimeListService.updateUserAnimeList(userAnimeList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAnimeList(@PathVariable UUID id) {
        userAnimeListService.deleteUserAnimeList(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/anime/{animeId}")
    public ResponseEntity<UserAnimeListDTO> addAnimeToList(
            @PathVariable UUID userId,
            @PathVariable UUID animeId,
            @RequestParam ListType listType) {
        UserAnimeListDTO result = userAnimeListService.addAnimeToList(userId, animeId, listType);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}/anime/{animeId}")
    public ResponseEntity<Void> removeAnimeFromList(
            @PathVariable UUID userId,
            @PathVariable UUID animeId,
            @RequestParam ListType listType) {
        userAnimeListService.removeAnimeFromList(userId, animeId, listType);
        return ResponseEntity.noContent().build();
    }
} 
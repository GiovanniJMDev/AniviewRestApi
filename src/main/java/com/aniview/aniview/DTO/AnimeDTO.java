package com.aniview.aniview.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimeDTO extends AnimeBasicDTO {
    private String description;
    private List<String> platforms;
    private Integer yearStarted;
    private Integer yearEnded;
    private Integer seasons;
    private List<Integer> episodesPerSeason;
    private Integer totalViews;
    private Integer weeklyViews;
    private List<UserDTO> users;

    public AnimeDTO() {
        super();
    }

    public AnimeDTO(UUID id, String title, String image, List<String> genres, double rating) {
        super(id, title, image, genres, rating);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public Integer getYearStarted() {
        return yearStarted;
    }

    public void setYearStarted(Integer yearStarted) {
        this.yearStarted = yearStarted;
    }

    public Integer getYearEnded() {
        return yearEnded;
    }

    public void setYearEnded(Integer yearEnded) {
        this.yearEnded = yearEnded;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public List<Integer> getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(List<Integer> episodesPerSeason) {
        this.episodesPerSeason = episodesPerSeason;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public Integer getWeeklyViews() {
        return weeklyViews;
    }

    public void setWeeklyViews(Integer weeklyViews) {
        this.weeklyViews = weeklyViews;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
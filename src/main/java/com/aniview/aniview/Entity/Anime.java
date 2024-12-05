package com.aniview.aniview.Entity;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "anime")
public class Anime {
    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "image", nullable = false, length = Integer.MAX_VALUE)
    private String image;

    // Cambiado de Map<String, Object> a List<String> para manejar arrays JSON
    @Column(name = "genres", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> genres;


    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;

    // Cambiado de Map<String, Object> a List<String> para manejar arrays JSON
    @Column(name = "platforms", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> platforms;


    @Column(name = "year_started", nullable = false)
    private Integer yearStarted;

    @Column(name = "year_ended")
    private Integer yearEnded;

    @Column(name = "seasons", nullable = false)
    private Integer seasons;

    // Cambiado de Map<String, Object> a List<Object> para manejar arrays JSON
    @Column(name = "episodes_per_season", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Object> episodesPerSeason;

    @Column(name = "total_views", nullable = false)
    private Integer totalViews;

    @Column(name = "weekly_views", nullable = false)
    private Integer weeklyViews;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "anime")
    private List<UserAnimeList> userLists;

    public Anime() {
        this.totalViews = 0;
        this.weeklyViews = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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

    public List<Object> getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(List<Object> episodesPerSeason) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<UserAnimeList> getUserLists() {
        return userLists;
    }

    public void setUserLists(List<UserAnimeList> userLists) {
        this.userLists = userLists;
    }
}
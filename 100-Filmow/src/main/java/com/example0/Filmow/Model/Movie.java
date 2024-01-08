package com.example0.Filmow.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
@Component
public class Movie {
    @GeneratedValue
    @Id
    private Long id;
    private Long filmwebId;
    private Long tmdbId;
    private String title;
    @OneToOne(mappedBy = "movie")
    private FilmwebMovie filmwebMovie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilmwebId() {
        return filmwebId;
    }

    public void setFilmwebId(Long filmwebId) {
        this.filmwebId = filmwebId;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FilmwebMovie getFilmwebMovie() {
        return filmwebMovie;
    }

    public void setFilmwebMovie(FilmwebMovie filmwebMovie) {
        this.filmwebMovie = filmwebMovie;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

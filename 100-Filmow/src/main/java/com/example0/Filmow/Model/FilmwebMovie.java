package com.example0.Filmow.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
public class FilmwebMovie {
    @Id
    private Long filmwebId;
    private String rating;
    private Long ratingCount;
    @OneToOne
    private Movie movie;

    public Long getFilmwebId() {
        return filmwebId;
    }

    public void setFilmwebId(Long filmwebId) {
        this.filmwebId = filmwebId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

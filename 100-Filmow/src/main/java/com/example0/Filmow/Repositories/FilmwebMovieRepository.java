package com.example0.Filmow.Repositories;

import com.example0.Filmow.Model.FilmwebMovie;
import com.example0.Filmow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmwebMovieRepository extends JpaRepository<FilmwebMovie,Long> {
    FilmwebMovie findByFilmwebId(Long filmwebId);

}

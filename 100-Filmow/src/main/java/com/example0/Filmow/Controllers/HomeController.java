package com.example0.Filmow.Controllers;

import com.example0.Filmow.Model.FilmwebMovie;
import com.example0.Filmow.Model.Movie;
import com.example0.Filmow.Repositories.FilmwebMovieRepository;
import com.example0.Filmow.Repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class HomeController {
    private final FilmwebMovieRepository filmwebMovieRepository;
    private final MovieRepository movieRepository;

    @GetMapping("/")
    @ResponseBody
    public String Display(){
        Optional<Movie> movieOptional = movieRepository.findById((long)952);
        String dane1 = new String();
        String dane2 = new String();
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            dane1 = "\ntytul " + movie.getTitle() + "\nid generowane " + movie.getId() + "\nfilmweb id " + movie.getFilmwebId();
            Optional<FilmwebMovie> filmwebMovie = Optional.ofNullable(filmwebMovieRepository.findByFilmwebId(movie.getFilmwebId()));
            if (filmwebMovie.isPresent()) {
                FilmwebMovie filmwebMovie1 = filmwebMovie.get();
                dane2 = "\nocena " + filmwebMovie1.getRating() + "\n liczba ocen " + filmwebMovie1.getRatingCount();
            }
        }

        return  dane1 + dane2 ;
    }
}

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
    String dane1;
    String dane2;
    @GetMapping("/")
    @ResponseBody
    public String Display(){
        Optional<Movie> movieOptional = movieRepository.findById((long)952);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            dane1 = movie.toString();
            Optional<FilmwebMovie> filmwebMovie = Optional.ofNullable(filmwebMovieRepository.findByFilmwebId(movie.getFilmwebId()));
            if (filmwebMovie.isPresent()) {
                FilmwebMovie filmwebMovie1 = filmwebMovie.get();
                dane2 = filmwebMovie1.toString();
            }
        }

        return "Przykłądowe wypisanie Filmu o id (generowanym):" + dane1 + dane2 ;
    }
}

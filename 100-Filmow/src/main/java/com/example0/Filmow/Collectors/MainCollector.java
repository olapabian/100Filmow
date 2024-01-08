package com.example0.Filmow.Collectors;

import com.example0.Filmow.JSONs.InfoRequest;

import com.example0.Filmow.JSONs.RatingRequest;
import com.example0.Filmow.Model.FilmwebMovie;
import com.example0.Filmow.Model.Movie;
import com.example0.Filmow.Repositories.FilmwebMovieRepository;
import com.example0.Filmow.Repositories.MovieRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
@Controller
@AllArgsConstructor
public class MainCollector {
    //loop 1 to 100
    // from info title and filmweb_id
    //from https://www.filmweb.pl/api/v1/film/837/rating rating and rating count
    // if ratin duzy biore film i rating count
    private final MovieRepository movieRepository;
    private final FilmwebMovieRepository filmwebMovieRepository;
    private Movie movieTemp;
    private FilmwebMovie filmwebMovieTemp;


    @GetMapping("/pobieraniedanych")
    @ResponseBody
    public String Collecting() {
        int number = 0;
        for (int filmId = 1; number <= 100; filmId++) {
            System.out.println(filmId);
            movieTemp = new Movie();
            filmwebMovieTemp = new FilmwebMovie();


            //-----------------Request rating (pobieram liczbe ocen i ocene)--------------------------------------------------
            try {
                URL url = new URL("https://www.filmweb.pl/api/v1/film/" + filmId + "/rating");

                com.example0.Filmow.Collectors.HttpCollector ratingCollector = new com.example0.Filmow.Collectors.HttpCollector(url);
                ratingCollector.Collect();
                if (!ratingCollector.getResponse().isEmpty()) {
                    String response = String.valueOf(ratingCollector.getResponse());
                    //użycie biblioteki Gson do deserializacji JSONów
                    Gson gson = new Gson();
                    // Po deserializacji JSONa do obiektu RatingRequest
                    RatingRequest ratingRequest = gson.fromJson(response, RatingRequest.class);
                    // Pobierz ocene z obiektu ratingRequest
                    // Konwersja wartości z ratingRequest.getRate() na String
                    String ratingAsString = String.valueOf(ratingRequest.getRate());
                    filmwebMovieTemp.setRating(ratingAsString);

                    // Pobierz liczbe ocen z obiektu ratingRequest
                    filmwebMovieTemp.setRatingCount(ratingRequest.getCount());
                    double rating = Double.parseDouble(filmwebMovieTemp.getRating());
                    if (rating < 7.5) //jesli ocena jest mniejsza niz 8 idziemy dalej (nie bierzemy tego filmuu)
                    {
                        continue;
                    }
                    if (filmwebMovieTemp.getRatingCount() < 200000)//jesli liczba ocen jest mniejsza niz 200 000 idziemy dalej (nie bierzemy tego filmuu)
                    {
                        continue;
                    }
                }
                else continue;

                //----------------Zapisuje ID filmwebowe------------------------------------
                movieTemp.setFilmwebId((long) filmId);
                filmwebMovieTemp.setFilmwebId((long) filmId);

                //----------------------------Request info (pobieram tylko tytuł)-------------------------------------
                URL url1 = new URL("https://www.filmweb.pl/api/v1/film/" + filmId + "/info");

                com.example0.Filmow.Collectors.HttpCollector infoCollector1 = new com.example0.Filmow.Collectors.HttpCollector(url1);
                infoCollector1.Collect();
                if (!infoCollector1.getResponse().isEmpty()) {
                    String response1 = String.valueOf(infoCollector1.getResponse());

                    //użycie biblioteki Gson do deserializacji JSONów
                    Gson gson1 = new Gson();
                    // Po deserializacji JSONa do obiektu InfoRequest
                    InfoRequest infoRequest1 = gson1.fromJson(response1, InfoRequest.class);

                    // Pobierz tytuł z obiektu InfoRequest
                    movieTemp.setTitle(infoRequest1.getTitle());
                    movieRepository.save(movieTemp);
                    filmwebMovieTemp.setMovie(movieTemp);
                    //Zapisanie ddo bazy danych
                    filmwebMovieRepository.save(filmwebMovieTemp);

                    //WYpisuje w terminal żeby zobaczyć co sie Zapisuje
                    System.out.println("tytul: " + movieTemp.getTitle());
                    System.out.println("filmwebID z tabeli movie: " + movieTemp.getFilmwebId());
                    System.out.println("filmwebID z tabeli filmweb: " + filmwebMovieTemp.getFilmwebId());
                    System.out.println("Ocena: " + filmwebMovieTemp.getRating());
                    System.out.println("Liczba ocen: " + filmwebMovieTemp.getRatingCount());
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    number++;
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        return "pobrano dane";
    }
}

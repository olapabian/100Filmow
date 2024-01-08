package com.example0.Filmow;

import com.example0.Filmow.Collectors.MainCollector;
import com.example0.Filmow.Repositories.FilmwebMovieRepository;
import com.example0.Filmow.Repositories.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

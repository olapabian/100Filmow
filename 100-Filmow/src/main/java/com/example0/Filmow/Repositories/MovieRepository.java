package com.example0.Filmow.Repositories;

import com.example0.Filmow.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository <Movie,Long> {
}

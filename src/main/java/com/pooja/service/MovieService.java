package com.pooja.service;

import com.pooja.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie saveMovie(Movie movie);
    Optional<Movie> getMovieById(String id);
    List<Movie> getAllMovies();
    Movie updateMovie(String id, Movie movie);
    void deleteMovie(String id);
}


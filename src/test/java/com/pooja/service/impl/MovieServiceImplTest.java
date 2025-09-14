package com.pooja.service.impl;

import com.pooja.model.Movie;
import com.pooja.repository.MovieRepository;
import com.pooja.service.MovieService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieServiceImplTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnMovie_whenIdExists(){

        String movieId = "movie1";
        com.pooja.entity.Movie movie = new com.pooja.entity.Movie();
        movie.setId(movieId);
        movie.setDuration(10);
        movie.setGenre("Comedy");
        movie.setTitle("ABC");

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Optional<Movie> responseMovie = movieService.getMovieById(movieId);

        // Assert
        assertEquals(movieId, responseMovie.get().getId());
        assertEquals(10, responseMovie.get().getDuration());
        assertEquals("ABC", responseMovie.get().getTitle());
        assertEquals("Comedy", responseMovie.get().getGenre());


    }

    @Test
    void shouldSaveMovie_whenNewId(){

        String movieId = "movie1";
        com.pooja.entity.Movie mockMovie = new com.pooja.entity.Movie();
        mockMovie.setId(movieId);
        mockMovie.setDuration(10);
        mockMovie.setGenre("Comedy");
        mockMovie.setTitle("ABC");

        when(movieRepository.save(mockMovie)).thenReturn(mockMovie);

        Movie movie1 = new Movie();
        Movie responseMovie = movieService.saveMovie(movie1);

        // Assert
        assertEquals(movieId, responseMovie.getId());
        assertEquals(10,responseMovie.getDuration());
        assertEquals("ABC", responseMovie.getTitle());
        assertEquals("Comedy", responseMovie.getGenre());


    }
    @Test
    void shouldFail_whenIdNull(){

        String movieId = "movie1";
        com.pooja.entity.Movie movie = new com.pooja.entity.Movie();
        movie.setId(movieId);
        movie.setDuration(10);
        movie.setGenre("Comedy");
        movie.setTitle("ABC");

        when(movieRepository.save(null)).thenReturn(null);

        Optional<Movie> responseMovie = movieService.getMovieById(movieId);

        // Asserts
        assertEquals(movieId, responseMovie.get().getId());
        assertEquals(10,responseMovie.get().getDuration());
        assertEquals("ABC", responseMovie.get().getTitle());
        assertEquals("Comedy", responseMovie.get().getGenre());


    }




    @Test
    void shouldFailOnGettingMovie_whenIdNotExists(){
        String movieId = "movie1";


        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        Optional<Movie> responseMovie = movieService.getMovieById(movieId);

        // Assert
        assertEquals(null, responseMovie.get().getId());
        assertEquals(0, responseMovie.get().getDuration());
        assertEquals(null, responseMovie.get().getTitle());
        assertEquals(null, responseMovie.get().getGenre());


    }


}





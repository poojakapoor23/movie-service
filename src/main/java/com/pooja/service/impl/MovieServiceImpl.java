package com.pooja.service.impl;

import com.pooja.model.Movie;
import com.pooja.repository.MovieRepository;
import com.pooja.service.MovieService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo ;

    public MovieServiceImpl(MovieRepository repo) {
        this.repo = repo;
    }

    @Caching(
            put = { @CachePut(value = "movies", key = "#result.id") },
            evict = { @CacheEvict(value = "moviesAll", allEntries = true) }
    )
    @Override
    public Movie saveMovie(Movie movie) {
        com.pooja.entity.Movie saved = repo.save(convertModel2Entity(movie));
        convertEntity2Model(movie, saved);
        return movie;
    }

    @Cacheable(value = "movies", key = "#id", unless = "#result.orElse(null) == null")
    @Override
    public Optional<Movie> getMovieById(String id) {
        return repo.findById(id).map(entity -> {
            Movie m = new Movie();
            convertEntity2Model(m, entity);
            return m;
        });
    }

    @Cacheable(value = "moviesAll", unless = "#result == null || #result.size() == 0")
    @Override
    public List<Movie> getAllMovies() {
        return repo.findAll().stream().map(entity -> {
            Movie m = new Movie();
            convertEntity2Model(m, entity);
            return m;
        }).collect(Collectors.toList());
    }

    @Caching(
            put = { @CachePut(value = "movies", key = "#id") },
            evict = { @CacheEvict(value = "moviesAll", allEntries = true) }
    )
    @Override
    public Movie updateMovie(String id, Movie movie) {
        if(repo.existsById(id)){
            movie.setId(id);
            com.pooja.entity.Movie saved = repo.save(convertModel2Entity(movie));
            convertEntity2Model(movie, saved);
            return movie;
        }
        return null;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "movies", key = "#id"),
                    @CacheEvict(value = "moviesAll", allEntries = true)
            }
    )
    @Override
    public void deleteMovie(String id) {
        repo.deleteById(id);
    }

    private static void convertEntity2Model(Movie movieModel, com.pooja.entity.Movie movieEntity) {
        if (movieEntity != null) {
            movieModel.setId(movieEntity.getId());
            movieModel.setTitle(movieEntity.getTitle());
            movieModel.setGenre(movieEntity.getGenre());
            movieModel.setDuration(movieEntity.getDuration());
        }
    }

    private static com.pooja.entity.Movie convertModel2Entity(Movie movieModel) {
        com.pooja.entity.Movie movieEntity = new com.pooja.entity.Movie();
        movieEntity.setId(movieModel.getId());
        movieEntity.setTitle(movieModel.getTitle());
        movieEntity.setGenre(movieModel.getGenre());
        movieEntity.setDuration(movieModel.getDuration());
        return movieEntity;
    }
}

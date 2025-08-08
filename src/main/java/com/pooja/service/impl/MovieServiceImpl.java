package com.pooja.service.impl;

import com.pooja.model.Movie;
import com.pooja.repository.MovieRepository;
import com.pooja.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo ;

    public MovieServiceImpl(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        repo.save(convertModel2Entity(movie));
        return movie;
    }

    private static com.pooja.entity.Movie convertModel2Entity(Movie movieModel) {
        com.pooja.entity.Movie movieEntity = new com.pooja.entity.Movie();
        movieEntity.setId(movieModel.getId());
        movieEntity.setTitle(movieModel.getTitle());
        movieEntity.setGenre(movieModel.getGenre());
        movieEntity.setDuration(movieModel.getDuration());
        return movieEntity;
    }

    @Override
    public Optional<Movie> getMovieById(String id) {
        Optional<com.pooja.entity.Movie> movieEntity = repo.findById(id);
        Movie movieModel = new Movie();
        convertEntity2Model(movieModel,movieEntity.orElse(null));
        return Optional.of(movieModel);
    }
    private static void convertEntity2Model(Movie movieModel, com.pooja.entity.Movie movieEntity) {
        if (movieEntity != null) {

            movieModel.setId(movieEntity.getId());
            movieModel.setTitle(movieEntity.getTitle());
            movieModel.setGenre(movieEntity.getGenre());
            movieModel.setDuration(movieEntity.getDuration());


        }
    }
    @Override
    public List<Movie> getAllMovies() {
        return List.of();
    }

    @Override
    public Movie updateMovie(String id, Movie movie) {


        if(repo.existsById(id)){
            repo.save(convertModel2Entity(movie));
            return movie;
        }
        return null;
    }
    @Override
    public void deleteMovie(String id) {
        repo.deleteById(id);
    }

}

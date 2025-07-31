package com.pooja.controller;

import com.pooja.model.Movie;
import com.pooja.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    MovieService movieService;

    @Operation(summary = "Create a new movie", description = "Accepts a Movie JSON and returns success message")
    @ApiResponse(responseCode = "200", description = "Movie successfully created")
    @PostMapping
    public ResponseEntity<String> createMovie(@Valid @RequestBody Movie movie) {

//        return ResponseEntity.ok("User Created: " + movie.getName());
        return ResponseEntity.ok(movieService.saveMovie(movie).getId());
    }
    @Operation(summary = "Get Movie by ID", description = "Fetch a Movie based on the ID")
    @ApiResponse(responseCode = "200", description = "Movie fetched successfully")
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String id) {
//        return ResponseEntity.ok("Movie fetched with ID: " + id);
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update movie by ID", description = "Update existing movie details")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable String id, @Valid @org.springframework.web.bind.annotation.RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie).getId());
    }

    @Operation(summary = "Delete Movie by ID", description = "Deletes a movie based on the ID")
    @ApiResponse(responseCode = "200", description = "Movie deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie Deleted with ID: " + id);
    }
}

package com.sundaydevblog.restservice.controller;

import com.sundaydevblog.restservice.model.Movie;
import com.sundaydevblog.restservice.model.Review;
import com.sundaydevblog.restservice.service.MovieServiceV1;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieControllerV1 {

    private MovieServiceV1 movieServiceV1;

    @Autowired
    public MovieControllerV1(MovieServiceV1 movieServiceV1) {
        this.movieServiceV1 = movieServiceV1;
    }

    // Return a list of movies
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getAllMovies() {
        return movieServiceV1.getAllMovies();
    }

    // Return a specific movie
    @GetMapping(
            path = ("/{movieId}"),
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(@PathVariable("movieId") Long movieId) throws NotFoundException {
        return movieServiceV1.getMovie(movieId);
    }

    // Create a new movie
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMovie(@RequestBody Movie movie) {
        movieServiceV1.addMovie(movie);
    }

    // Create a review to the movie
    @PostMapping(
            path = ("/{movieId}/reviews"),
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReview(
            @PathVariable("movieId") Long movieId,
            @RequestBody Review review) throws NotFoundException {
        movieServiceV1.addReview(movieId, review);
    }

    // Update movie
    @PutMapping(
            path = ("/{movieId}"),
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateMovie(
            @PathVariable("movieId") Long movieId,
            @RequestBody Movie movie) throws NotFoundException {
        movieServiceV1.updateMovie(movieId, movie);
    }

    // Update review of the movie
    @PutMapping(
            path = ("/{movieId}/reviews/{reviewId}"),
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateReview(
            @PathVariable("movieId") Long movieId,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody Review review) throws NotFoundException {
        movieServiceV1.updateReview(movieId, reviewId, review);
    }

    // Remove movie
    @DeleteMapping(
            path = "/{movieId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMovie(@PathVariable("movieId") Long movieId) throws NotFoundException {
        movieServiceV1.deleteMovie(movieId);
    }

    // Remove movie review
    @DeleteMapping(
            path = "/{movieId}/reviews/{reviewId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReview(
            @PathVariable("movieId") Long movieId,
            @PathVariable("reviewId") Long reviewId) throws NotFoundException {
        movieServiceV1.deleteReview(movieId, reviewId);
    }


}

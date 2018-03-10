package com.sundaydevblog.restservice.controller;

import com.sundaydevblog.restservice.model.Movie;
import com.sundaydevblog.restservice.model.Review;
import com.sundaydevblog.restservice.service.MovieServiceV2;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/movies")
public class MovieControllerV2 {

    private MovieServiceV2 movieServiceV2;

    @Autowired
    public MovieControllerV2(MovieServiceV2 movieServiceV2) {
        this.movieServiceV2 = movieServiceV2;
    }

    // Return a list of movies
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllMoviesExcludeReviews() {
        return movieServiceV2.getAllMoviesExcludeReviews();
    }

    // Return a list of movies and reviews
    @GetMapping(path = "/reviews",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getAllMoviesIncludeRevimeews() {
        return movieServiceV2.getAllMoviesIncludeReviews();
    }

    // Return a list movies with given title (ignore case)
    @GetMapping(params = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMoviesByTitle(
            @RequestParam(value = "search") String title) {
        return movieServiceV2.getMoviesByTitle(title);
    }

    // Return a list of movies released in the given year
    @GetMapping(params = "year", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMoviesByYear(
            @RequestParam(value = "year") int year) {
        return movieServiceV2.getMoviesByYear(year);
    }

    // Return a list of movies released in the grater than or equal given year
    @GetMapping(params = "year>", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMoviesYearGreaterEqual(
            @RequestParam(value = "year>") int year) {
        return movieServiceV2.getMoviesYearGreaterEqual(year);
    }

    // Return a list of movies released in the less than or equal given year
    @GetMapping(params = "year<", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMoviesYearLessEqual(
            @RequestParam(value = "year<") int year) {
        return movieServiceV2.getMoviesYearLessEqual(year);
    }

    // Return a specific movie
    @GetMapping(
            path = ("/{movieId}"),
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(@PathVariable("movieId") Long movieId) throws NotFoundException {
        return movieServiceV2.getMovie(movieId);
    }

    // Create a new movie
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMovie(@RequestBody Movie movie) {
        movieServiceV2.addMovie(movie);
    }

    // Create a review to the movie
    @PostMapping(
            path = ("/{movieId}/reviews"),
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReview(
            @PathVariable("movieId") Long movieId,
            @RequestBody Review review) throws NotFoundException {
        movieServiceV2.addReview(movieId, review);
    }

    // Update movie
    @PutMapping(
            path = ("/{movieId}"),
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateMovie(
            @PathVariable("movieId") Long movieId,
            @RequestBody Movie movie) throws NotFoundException {
        movieServiceV2.updateMovie(movieId, movie);
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
        movieServiceV2.updateReview(movieId, reviewId, review);
    }

    // Remove movie
    @DeleteMapping(
            path = "/{movieId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMovie(@PathVariable("movieId") Long movieId) throws NotFoundException {
        movieServiceV2.deleteMovie(movieId);
    }

    // Remove movie review
    @DeleteMapping(
            path = "/{movieId}/reviews/{reviewId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReview(
            @PathVariable("movieId") Long movieId,
            @PathVariable("reviewId") Long reviewId) throws NotFoundException {
        movieServiceV2.deleteReview(movieId, reviewId);
    }

    // Return the number of movies in the database
    @GetMapping(
            path = ("/count"),
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String countMovies() {
        return movieServiceV2.countMovies();
    }

}

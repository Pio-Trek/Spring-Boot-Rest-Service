package com.sundaydevblog.restservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sundaydevblog.restservice.Serializer.MovieSerializer;
import com.sundaydevblog.restservice.model.Movie;
import com.sundaydevblog.restservice.model.Review;
import com.sundaydevblog.restservice.repository.MovieRepository;
import com.sundaydevblog.restservice.repository.ReviewRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceV2 {

    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public MovieServiceV2(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    // Return a list of movies
    public String getAllMoviesExcludeReviews() {
        List<Movie> movies = movieRepository.findAll();
        return excludeReviews(movies);
    }

    // Return a list of movies and reviews
    public List<Movie> getAllMoviesIncludeReviews() {
        return movieRepository.findAll();
    }

    // Return a list movies with given title (ignore case)
    public String getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        return excludeReviews(movies);
    }

    // Return a list of movies released in the given year
    public String getMoviesByYear(int year) {
        List<Movie> movies = movieRepository.findByYear(year);
        return excludeReviews(movies);
    }

    // Return a list of movies released in the grater than or equal given year
    public String getMoviesYearGreaterEqual(int year) {
        List<Movie> movies = movieRepository.findByYearGreaterThanEqual(year);
        return excludeReviews(movies);
    }

    // Return a list of movies released in the less than or equal given year
    public String getMoviesYearLessEqual(int year) {
        List<Movie> movies = movieRepository.findByYearLessThanEqual(year);
        return excludeReviews(movies);
    }

    // Return a specific movie
    public Movie getMovie(Long movieId) throws NotFoundException {
        Long id = validateMovie(movieId);
        return movieRepository.findOne(id);
    }

    // Create a new movie
    public void addMovie(Movie movie) {
        this.movieRepository.save(movie);
    }

    // Create a review to the movie
    public void addReview(Long movieId, Review review) throws NotFoundException {
        Movie movie = getMovie(movieId);
        review.setMovie(movie);
        this.reviewRepository.save(review);
    }

    // Update movie
    public void updateMovie(Long movieId, Movie movie) throws NotFoundException {
        Long id = validateMovie(movieId);
        movie.setId(id);
        movieRepository.save(movie);
    }

    // Update review of the movie
    public void updateReview(Long movieId, Long reviewId, Review review) throws NotFoundException {
        if (doesReviewBelongsToMovie(movieId, reviewId)) {
            review.setId(reviewId);
            reviewRepository.save(review);
        }
    }

    // Remove movie
    public void deleteMovie(Long movieId) throws NotFoundException {
        Long id = validateMovie(movieId);
        movieRepository.delete(id);
    }

    // Remove movie review
    public void deleteReview(Long movieId, Long reviewId) throws NotFoundException {
        if (doesReviewBelongsToMovie(movieId, reviewId))
            reviewRepository.delete(reviewId);
    }

    // Return the number of movies in the database
    public String countMovies() {
        return movieRepository.countAllMovies();
    }


    // Check if specific review belongs to specific movie
    private boolean doesReviewBelongsToMovie(Long movieId, Long reviewId) throws NotFoundException {
        this.validateMovie(movieId);

        Review review = reviewRepository.findOne(reviewId);

        if (Objects.equals(movieId, review.getMovie().getId())) {
            return true;
        } else {
            throw new NotFoundException
                    ("Movie with ID: '" + movieId + "' does not contain review with ID: '" + review.getId() + "'");
        }

    }

    // Check if specific movie exist in the database
    private Long validateMovie(Long movieId) throws NotFoundException {
        return Optional.ofNullable(movieRepository.findOne(movieId))
                .map(Movie::getId)
                .orElseThrow(() -> new NotFoundException
                        ("Movie with ID: '" + movieId + "' not found"));
    }

    // Exclude reviews from JSON output for the Movie class
    private String excludeReviews(List<Movie> movies) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Movie.class, new MovieSerializer());
        mapper.registerModule(module);
        String result = null;
        try {
            result = mapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}


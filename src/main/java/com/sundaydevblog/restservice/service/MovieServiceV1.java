package com.sundaydevblog.restservice.service;

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
public class MovieServiceV1 {

    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public MovieServiceV1(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    // Return a list of movies
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
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

}

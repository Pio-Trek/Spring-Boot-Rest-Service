package com.sundaydevblog.restservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, updatable = false)
    private Movie movie;

    public Review() {
    }

    public Review(final String comment, final Movie movie) {
        this.comment = comment;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

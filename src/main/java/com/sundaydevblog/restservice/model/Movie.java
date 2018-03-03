package com.sundaydevblog.restservice.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotNull
    private Integer year;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    public Movie() {
    }

    public Movie(final String title, final Integer year) {
        this.title = title;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}

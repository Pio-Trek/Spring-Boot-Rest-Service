package com.sundaydevblog.restservice.repository;

import com.sundaydevblog.restservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

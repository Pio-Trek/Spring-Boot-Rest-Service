package com.sundaydevblog.restservice.repository;

import com.sundaydevblog.restservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    //    @Query(value = "SELECT * FROM movie WHERE LOWER(title) LIKE LOWER('%a%')", nativeQuery = true)
    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Query("select m from Movie m where m.year = :year")
    List<Movie> findByYear(@Param("year") int year);

    List<Movie> findByYearGreaterThanEqual(int year);

    List<Movie> findByYearLessThanEqual(int year);

    @Query(value = "SELECT COUNT(*) FROM movie;", nativeQuery = true)
    String countAllMovies();
}

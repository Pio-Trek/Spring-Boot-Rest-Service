package com.sundaydevblog.restservice.repository;

import com.sundaydevblog.restservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

package com.entbooking.eticketing.repository;

import com.entbooking.eticketing.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByMovieName(String name);
}
package com.entbooking.eticketing.repository;

import com.entbooking.eticketing.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Theater findByAddress(String address);

    @Query(value = "Select t from theater t", nativeQuery = true)
    List<Theater> getShowDetailsByMovieName(String movieName);
}
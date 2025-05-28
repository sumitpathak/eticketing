package com.entbooking.eticketing.repository;

import com.entbooking.eticketing.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "select time from shows where date = :date and movie_id = :movieId and theater_id = :theaterId" , nativeQuery = true)
    public List<Time> getShowTimingsOnDate(@Param("date") Date date, @Param("theaterId")Long theaterId, @Param("movieId")Long movieId);

    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1" , nativeQuery = true)
    public Long getMostShowsMovie();

    @Query(value = "select * from shows where movie_id = :movieId" , nativeQuery = true)
    public List<Show> getAllShowsOfMovie(@Param("movieId")Long movieId);

    @Query(nativeQuery = true, value = "Select * from shows where movie_id=:movieId")
    List<Show> getAllShowsOfMovieByName(Long movieId);

    List<Show> findShowByMovie_movieName(String movieName);
}

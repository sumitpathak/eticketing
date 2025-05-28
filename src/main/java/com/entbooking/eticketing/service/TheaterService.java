package com.entbooking.eticketing.service;

import com.entbooking.eticketing.dto.request.TheaterEntryDto;
import com.entbooking.eticketing.dto.request.TheaterSeatEntryDto;
import com.entbooking.eticketing.dto.response.MovieShowDetailsResponse;

import java.util.List;

public interface TheaterService {
    public String addTheater(TheaterEntryDto theaterEntryDto) throws RuntimeException;

    public String addTheaterSeat(TheaterSeatEntryDto entryDto) throws RuntimeException;

    List<MovieShowDetailsResponse> getMovieShowDetails(Long movieId) throws Exception;
}
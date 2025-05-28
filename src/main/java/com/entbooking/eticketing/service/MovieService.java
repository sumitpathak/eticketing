package com.entbooking.eticketing.service;

import com.entbooking.eticketing.dto.request.MovieEntryDto;
import com.entbooking.eticketing.entity.Movie;

import java.util.List;

public interface MovieService {
    public String addMovie(MovieEntryDto movieEntryDto) throws RuntimeException;
    public Long totalCollection(Long movieId) throws RuntimeException;
}

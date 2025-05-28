package com.entbooking.eticketing.service.impl;

import com.entbooking.eticketing.dto.request.MovieEntryDto;
import com.entbooking.eticketing.entity.Movie;
import com.entbooking.eticketing.entity.Show;
import com.entbooking.eticketing.entity.Ticket;
import com.entbooking.eticketing.repository.MovieRepository;
import com.entbooking.eticketing.repository.ShowRepository;
import com.entbooking.eticketing.service.MovieService;
import com.entbooking.eticketing.transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws RuntimeException {
        if(movieRepository.findByMovieName(movieEntryDto.getMovieName()) != null) {
            if(movieRepository.findByMovieName(movieEntryDto.getMovieName()).getLanguage().equals(movieEntryDto.getLanguage())){
                throw new RuntimeException("Movie already present");
            }
        }
        Movie movie = MovieTransformer.movieDtoToMovie(movieEntryDto);
        movieRepository.save(movie);
        return "The movie has been added successfully";
    }

    public Long totalCollection(Long movieId) throws RuntimeException {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if(movieOpt.isEmpty()) {
            throw new RuntimeException("Movie does not exist");
        }
        List<Show> showListOfMovie = showRepository.getAllShowsOfMovie(movieId);
        long ammount = 0;
        for(Show show : showListOfMovie) {
            for(Ticket ticket : show.getTicketList()) {
                ammount += (long)ticket.getTotalTicketsPrice();
            }
        }
        return ammount;
    }

}

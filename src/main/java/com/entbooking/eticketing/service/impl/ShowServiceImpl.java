package com.entbooking.eticketing.service.impl;

import com.entbooking.eticketing.dto.request.ShowEntryDto;
import com.entbooking.eticketing.dto.request.ShowSeatEntryDto;
import com.entbooking.eticketing.dto.request.ShowTimingsDto;
import com.entbooking.eticketing.entity.*;
import com.entbooking.eticketing.enums.SeatType;
import com.entbooking.eticketing.repository.MovieRepository;
import com.entbooking.eticketing.repository.ShowRepository;
import com.entbooking.eticketing.repository.TheaterRepository;
import com.entbooking.eticketing.service.ShowService;
import com.entbooking.eticketing.transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(ShowEntryDto showEntryDto) throws RuntimeException{
        Show show = ShowTransformer.showDtoToShow(showEntryDto);

        Optional<Movie> movieOpt = movieRepository.findById(showEntryDto.getMovieId());
        if(movieOpt.isEmpty()) {
            throw new RuntimeException("Movie does not exist");
        }
        Optional<Theater> theaterOpt = theaterRepository.findById(showEntryDto.getTheaterId());
        if(theaterOpt.isEmpty()) {
            throw new RuntimeException("Theater does not exist");
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatEntryDto showSeatEntryDto) throws RuntimeException{
        Optional<Show> showOpt = showRepository.findById(showSeatEntryDto.getShowId());
        if(showOpt.isEmpty()) {
            throw new RuntimeException("Show does not exist");
        }
        Show show = showOpt.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = getShowSeat(showSeatEntryDto, theaterSeat, show);

            showSeatList.add(showSeat);
        }
        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

    private static ShowSeat getShowSeat(ShowSeatEntryDto showSeatEntryDto, TheaterSeat theaterSeat, Show show) {
        ShowSeat showSeat = new ShowSeat();
        showSeat.setSeatNo(theaterSeat.getSeatNo());
        showSeat.setSeatType(theaterSeat.getSeatType());

        if(showSeat.getSeatType().equals(SeatType.CLASSIC)) {
            showSeat.setPrice((showSeatEntryDto.getPriceOfClassicSeat()));
        } else {
            showSeat.setPrice(showSeatEntryDto.getPriceOfPremiumSeat());
        }

        showSeat.setShow(show);
        showSeat.setIsAvailable(Boolean.TRUE);
        showSeat.setIsFoodContains(Boolean.FALSE);
        return showSeat;
    }

    public List<Time> showTimingsOnDate(ShowTimingsDto showTimingsDto) {
        Date date = showTimingsDto.getDate();
        Long theaterId = showTimingsDto.getTheaterId();
        Long movieId = showTimingsDto.getMovieId();
        return showRepository.getShowTimingsOnDate(date, theaterId, movieId);
    }

    public String movieHavingMostShows() {
        Long movieId = showRepository.getMostShowsMovie();
        return movieRepository.findById(movieId).get().getMovieName();
    }
}
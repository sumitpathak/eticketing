package com.entbooking.eticketing.service.impl;

import com.entbooking.eticketing.dto.request.TheaterEntryDto;
import com.entbooking.eticketing.dto.request.TheaterSeatEntryDto;
import com.entbooking.eticketing.dto.response.MovieShowDetailsResponse;
import com.entbooking.eticketing.entity.Show;
import com.entbooking.eticketing.entity.Theater;
import com.entbooking.eticketing.entity.TheaterSeat;
import com.entbooking.eticketing.enums.SeatType;
import com.entbooking.eticketing.repository.ShowRepository;
import com.entbooking.eticketing.repository.TheaterRepository;
import com.entbooking.eticketing.service.TheaterService;
import com.entbooking.eticketing.transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws RuntimeException {
        if (theaterRepository.findByAddress(theaterEntryDto.getAddress()) != null) {
            throw new RuntimeException("Theater is present on that address");
        }
        Theater theater = TheaterTransformer.theaterDtoToTheater(theaterEntryDto);

        theaterRepository.save(theater);
        return "Theater has been saved Successfully";
    }

    public String addTheaterSeat(TheaterSeatEntryDto entryDto) throws RuntimeException {
        if (theaterRepository.findByAddress(entryDto.getAddress()) == null) {
            throw new RuntimeException("Theater is not present on this address");
        }
        Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
        Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
        Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
        String address = entryDto.getAddress();

        Theater theater = theaterRepository.findByAddress(address);

        List<TheaterSeat> seatList = theater.getTheaterSeatList();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for (int i = 1; i <= noOfClassicSeat; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        for (int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        theaterRepository.save(theater);

        return "Theater Seats have been added successfully";
    }

    @Override
    public List<MovieShowDetailsResponse> getMovieShowDetails(Long movieId) throws Exception {
        List<Theater> theaters = theaterRepository.findAll();
        //theaterRepository.getShowDetailsByMovieName(movieName);
        List<Show> shows = showRepository.getAllShowsOfMovieByName(movieId);
        List<Show> shows1 = showRepository.findAll();
        if (theaters.isEmpty()) {
            throw new Exception("No record found for this movie");
        }
        List<MovieShowDetailsResponse> movieShowDetailsResponses = new ArrayList<>();
        theaters.forEach(t -> {
            MovieShowDetailsResponse showDetailsResponse = new MovieShowDetailsResponse();
            showDetailsResponse.setTheaterId(t.getId());
            showDetailsResponse.setTheaterName(t.getName());
            showDetailsResponse.setCity(t.getCity());
            t.getShowList().forEach(show -> {
                if (showDetailsResponse.getShowDate() == null) {
                    showDetailsResponse.setShowDate(show.getDate().toString());
                }
                if (show.getMovie()!=null && showDetailsResponse.getMovieName()==null){
                    showDetailsResponse.setMovieName(show.getMovie().getMovieName());
                }
                showDetailsResponse.getShowTime().add(show.getTime().toString());
            });
            movieShowDetailsResponses.add(showDetailsResponse);
        });
        return movieShowDetailsResponses;
    }

}
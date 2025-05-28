package com.entbooking.eticketing.controller;

import com.entbooking.eticketing.dto.request.TheaterEntryDto;
import com.entbooking.eticketing.dto.request.TheaterSeatEntryDto;
import com.entbooking.eticketing.dto.response.MovieShowDetailsResponse;
import com.entbooking.eticketing.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
//@PreAuthorize("hasRole('PARTNER')")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto) {
        try {
            String result = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatEntryDto entryDto) {
        try {
            String result = theaterService.addTheaterSeat(entryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowDetails/{movieId}")
    public ResponseEntity<List<MovieShowDetailsResponse>> getMovieShowDetails(@PathVariable Long movieId) {
        try {
            List<MovieShowDetailsResponse> movieShowDetailsResponse = theaterService.getMovieShowDetails(movieId);
            return new ResponseEntity<>(movieShowDetailsResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //@DeleteMapping()

}

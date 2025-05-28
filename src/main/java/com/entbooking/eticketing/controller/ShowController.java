package com.entbooking.eticketing.controller;

import com.entbooking.eticketing.dto.request.ShowEntryDto;
import com.entbooking.eticketing.dto.request.ShowSeatEntryDto;
import com.entbooking.eticketing.dto.request.ShowTimingsDto;
import com.entbooking.eticketing.entity.Show;
import com.entbooking.eticketing.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto) {
        try {
            String result = showService.addShow(showEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatEntryDto showSeatEntryDto) {
        try {
            String result = showService.associateShowSeats(showSeatEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showTimingsOnDate")
    public ResponseEntity<List<Time>> showTimingsOnDate(ShowTimingsDto showTimingsDto) {
        try {
            List<Time> result = showService.showTimingsOnDate(showTimingsDto);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movieHavingMostShows")
    public ResponseEntity<String> movieHavingMostShows() {
        try {
            String movie = showService.movieHavingMostShows();
            return new ResponseEntity<>(movie, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
